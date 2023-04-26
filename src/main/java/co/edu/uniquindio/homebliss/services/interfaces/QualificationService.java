package co.edu.uniquindio.homebliss.services.interfaces;

import co.edu.uniquindio.homebliss.dto.QualificationDTO;

public interface QualificationService {

   int createQualification(QualificationDTO qualificationDTO) throws Exception;

   boolean deleteQualification(int qualificationId);

}
