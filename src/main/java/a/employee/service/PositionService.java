package a.employee.service;

import a.employee.dto.PositionRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Position;
import a.employee.repository.PositionRepository;
import a.employee.utility.ValidationUtility;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository repo;
    private final ValidationUtility valid;
    public PositionService(PositionRepository repo, ValidationUtility valid) {
        this.repo = repo;
        this.valid = valid;
    }
    public void createPos(PositionRequestDTO a) throws CustomException {
        if (a.getAlw() < 0) throw new CustomException("Trợ cấp không được dưới 0!");
        if (valid.checkValid(a.getName())) throw new CustomException("Tên chức vụ không được để trống!");
        if (valid.checkValid(a.getId())) throw new CustomException("ID chức vụ không được để trống!");
        Optional<Position> p = repo.findById(a.getId());
        if (p.isPresent()) throw new CustomException("ID chức vụ đã tồn tại!");
        repo.save(new Position(a.getAlw(), a.getName(), a.getId()));
    }
}
