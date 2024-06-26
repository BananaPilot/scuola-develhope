package com.teamproject1.scuoledevelhope.classes.register;

import com.teamproject1.scuoledevelhope.classes.tutor.Tutor;
import com.teamproject1.scuoledevelhope.classes.tutor.repo.TutorDAO;
import com.teamproject1.scuoledevelhope.types.Interceptor;
import com.teamproject1.scuoledevelhope.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RegisterInterceptor extends Interceptor {

    private final Utils utils;

    private final TutorDAO tutorDAO;

    public RegisterInterceptor(Utils utils, TutorDAO tutorDAO) {
        this.utils = utils;
        this.tutorDAO = tutorDAO;
    }


    @Transactional
    public boolean handle(HttpServletRequest request) {
        Long registerId = Long.parseLong(request.getRequestURI().split("/")[3]);
        Tutor tutor = utils.isPresent(tutorDAO.findById(utils.getUserFromJwt(request.getHeader("Authorization")).getId()));
        for (Register registersInTutor : tutor.getRegisters()) {
            if (registersInTutor.getId().equals(registerId)) return true;
        }
        return false;
    }
}
