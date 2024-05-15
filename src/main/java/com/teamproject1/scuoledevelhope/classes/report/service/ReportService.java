package com.teamproject1.scuoledevelhope.classes.report.service;

import com.teamproject1.scuoledevelhope.classes.report.Report;
import com.teamproject1.scuoledevelhope.classes.report.dto.ReportDto;
import com.teamproject1.scuoledevelhope.classes.report.dto.ReportMapper;
import com.teamproject1.scuoledevelhope.classes.report.dto.ReportVoteDto;
import com.teamproject1.scuoledevelhope.classes.report.repo.ReportDao;
import com.teamproject1.scuoledevelhope.classes.vote.Vote;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteMapper;
import com.teamproject1.scuoledevelhope.classes.vote.repo.VoteDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportDao reportDao;
    private final VoteDAO voteDao;
    private final VoteMapper voteMapper;
    private final ReportMapper reportMapper;

    public ReportService(ReportDao reportDao, VoteDAO voteDao, VoteMapper voteMapper, ReportMapper reportMapper) {
        this.reportDao = reportDao;
        this.voteDao = voteDao;
        this.voteMapper = voteMapper;
        this.reportMapper = reportMapper;
    }

    @Transactional
    public ReportDto save(ReportVoteDto reportVoteDto) {
        List<Vote> voteList = voteDao.findBySubjectAndStudentId(reportVoteDto.getSubject(), reportVoteDto.getIdStudent());

        Float gradePointAverage = 0F;

        for (Vote element : voteList) {
            gradePointAverage += element.getEvaluation();
        }
        gradePointAverage /= voteList.size();

        ReportDto response = ReportDto.ReportDtoBuilder.aReportDto()
                .withGradePointAverage(gradePointAverage)
                .withVotes(voteMapper.toVoteResponseDto(voteList))
                .withConduct(reportVoteDto.getConduct())
                .withSubject(reportVoteDto.getSubject())
                .withIdStudent(reportVoteDto.getIdStudent())
                .build();

        reportDao.save(reportMapper.toReport(response));

        return response;
    }

    public ReportDto findReport(Long idStudent, String subject, int limit, int page) {
        Report report = reportDao.findByIdStudentAndSubject(subject);
        Page<Vote> voteList = voteDao.findBySubjectAndStudentId(report.getSubject(), report.getStudent().getId(), PageRequest.of(page, limit));

        return ReportDto.ReportDtoBuilder.aReportDto()
                .withGradePointAverage(report.getGradePointAverage())
                .withVotes(voteMapper.toVoteResponseDto(voteList.toList()))
                .withIdStudent(idStudent)
                .withSubject(subject)
                .withTotalPages(voteList.getTotalPages())
                .withTotalElements(voteList.getTotalElements())
                .withPage(voteList.getPageable().getPageNumber())
                .withPageSize(voteList.getPageable().getPageSize())
                .build();
    }

    public void update(String idStudent, String subject) throws ReportNotFound {
        // Trova il report corrente dello studente per la materia specificata
        Report report = reportDao.findByIdStudentAndSubject(idStudent, subject);

        // Verifica se il report esiste
        if (report != null) {
            // Aggiorna il voto nel report corrente
            report.getVote(subject);

            // Salva il report aggiornato
            reportDao.save(report);
        } else {
            // Se il report non esiste, solleva un'eccezione ReportNotFoundException
            throw new ReportNotFound("Report not found for the student: " + idStudent + " and subject: " + subject);
        }
    }

}
