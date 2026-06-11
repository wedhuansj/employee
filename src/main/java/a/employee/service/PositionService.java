package a.employee.service;

import a.employee.dto.PositionRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Position;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;
import a.employee.repository.GenericRepositoryImpl;
@Service
public class PositionService {
    private final GenericRepositoryImpl<Position> repo;
    private final ValidationUtility valid;
    public PositionService(GenericRepositoryImpl<Position> repo, ValidationUtility valid) {
        this.repo = repo;
        this.valid = valid;
    }
    public void createPos(PositionRequestDTO a) throws CustomException {
        if (a.getAlw() < 0) throw new CustomException("Trợ cấp không được dưới 0!");
        if (valid.checkValid(a.getName())) throw new CustomException("Tên chức vụ không được để trống!");
        if (valid.checkValid(a.getId())) throw new CustomException("ID chức vụ không được để trống!");
        Position p = repo.findById(a.getId(), Position.class);
        if (p != null) throw new CustomException("ID chức vụ đã tồn tại!");
        repo.add(new Position(a.getAlw(), a.getName(), a.getId()));
    }
}
