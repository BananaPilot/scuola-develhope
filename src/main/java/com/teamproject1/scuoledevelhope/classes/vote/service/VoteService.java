package com.teamproject1.scuoledevelhope.classes.vote.service;

import com.teamproject1.scuoledevelhope.classes.register.repo.RegisterDao;
import com.teamproject1.scuoledevelhope.classes.student.Student;
import com.teamproject1.scuoledevelhope.classes.student.repo.StudentDAO;
import com.teamproject1.scuoledevelhope.classes.vote.Vote;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteDTO;
import com.teamproject1.scuoledevelhope.classes.vote.dto.VoteMapper;
import com.teamproject1.scuoledevelhope.classes.vote.repo.VoteDAO;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import com.teamproject1.scuoledevelhope.types.errors.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteDAO voteDAO;
    private final VoteMapper voteMapper;

    public VoteService(VoteDAO voteDAO, VoteMapper voteMapper) {
        this.voteDAO = voteDAO;
        this.voteMapper = voteMapper;
    }

    public BaseResponseList<Vote> findAll() {

        return new BaseResponseList<>(voteDAO.findAll());
    }

    public BaseResponseElement<Vote> findById(Long id) {
        Optional<Vote> result = voteDAO.findById(id);
        if (result.isEmpty()) {
            throw new SQLException("Vote was not present");
        }
        return new BaseResponseElement<>(result.get());
    }

    public BaseResponseElement<Vote> save(Vote vote) {
        return new BaseResponseElement<>(voteDAO.save(vote));
    }

    public BaseResponseElement<Vote> deleteById(Long id) {
        Optional<Vote> temp = voteDAO.findById(id);

        if (temp.isEmpty()) {
            throw new SQLException("Vote was not present");
        }
        voteDAO.deleteById(id);

        return new BaseResponseElement<>(temp.get());
    }

    public BaseResponseElement<VoteDTO> add(VoteDTO voteDTO)  {
        Vote res = voteDAO.save(voteMapper.toVote(voteDTO));

        return new BaseResponseElement<>(voteDTO);
    }
}
