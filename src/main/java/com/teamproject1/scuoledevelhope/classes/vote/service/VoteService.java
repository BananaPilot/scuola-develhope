package com.teamproject1.scuoledevelhope.classes.vote.service;

import com.teamproject1.scuoledevelhope.classes.report.dto.ReportMapper;
import com.teamproject1.scuoledevelhope.classes.report.dto.ReportVoteDto;
import com.teamproject1.scuoledevelhope.classes.report.service.ReportService;
import com.teamproject1.scuoledevelhope.classes.student.Student;
import com.teamproject1.scuoledevelhope.classes.student.repo.StudentDAO;
import com.teamproject1.scuoledevelhope.classes.vote.Vote;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteDto;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteDtoList;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteMapper;
import com.teamproject1.scuoledevelhope.classes.vote.repo.VoteDAO;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.errors.SQLException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteDAO voteDAO;
    private final VoteMapper voteMapper;
    private final StudentDAO studentDAO;
    private final ReportService reportService;
    private final ReportMapper reportMapper;

    public VoteService(VoteDAO voteDAO, VoteMapper voteMapper, StudentDAO studentDAO, ReportService reportService, ReportMapper reportMapper) {
        this.voteDAO = voteDAO;
        this.voteMapper = voteMapper;
        this.studentDAO = studentDAO;
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }


    public VoteDtoList findByStudent(Long idStudent, int limit, int page) {
        Page<Vote> votes = voteDAO.findAllByStudentId(idStudent, PageRequest.of(page, limit));

        return VoteDtoList.VoteDtoListBuilder.aVoteDtoList()
                .withVotes(voteMapper.toListVoteDto(votes.toList()))
                .withHttpStatus(HttpStatus.OK)
                .withPage(votes.getPageable().getPageNumber())
                .withPageSize(votes.getPageable().getPageSize())
                .withTotalElements(votes.getTotalElements())
                .withTotalPages(votes.getTotalPages())
                .build();
    }

    public BaseResponseElement<VoteDto> deleteVote(Long idStudent, Long idVote) {
        Optional<Vote> voteR = voteDAO.findById(idVote);
        Optional<Student> studentR = studentDAO.findById(idStudent);

        if (voteR.isEmpty()) {
            throw new SQLException("Vote was not present");
        }
        if (studentR.isEmpty()) {
            throw new SQLException("Student was not present");
        }

        VoteDto voteDTO = voteMapper.toVoteDto(voteR.get());
        voteDAO.deleteVote(idStudent, idVote);

        return new BaseResponseElement<>(voteDTO);
    }

    @Transactional
    public BaseResponseElement<VoteDto> addVote(VoteDto voteDTO) {
        voteDAO.save(voteMapper.toVote(voteDTO));
        ReportVoteDto reportVoteDto = reportMapper.voteDtoToReportVoteDto(voteDTO);

        if (voteDTO.getIsCheckPoint()) {
            reportService.save(reportVoteDto);
        }

        return new BaseResponseElement<>(voteDTO);
    }
}
