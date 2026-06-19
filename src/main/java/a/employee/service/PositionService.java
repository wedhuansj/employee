package a.employee.service;

import a.employee.dto.PositionRequestDTO;
import a.employee.exception.CustomException;
import a.employee.model.Position;
import a.employee.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository repo;
    public PositionService(PositionRepository repo) { this.repo = repo; }
    public void createPos(PositionRequestDTO a) throws CustomException {
        Optional<Position> p = repo.findById(a.getId());
        if (p.isPresent()) throw new CustomException("ID chức vụ đã tồn tại!");
        repo.save(new Position(a.getAlw(), a.getName(), a.getId()));
    }
}
