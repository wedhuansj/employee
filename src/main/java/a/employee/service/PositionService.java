package a.employee.service;

import a.employee.model.Position;
import org.springframework.stereotype.Service;
import a.employee.repository.GenericRepositoryImpl;
@Service
public class PositionService {
    private final GenericRepositoryImpl<Position> repo;
    public PositionService(GenericRepositoryImpl<Position> repo) { this.repo = repo; }
    public void createPos(String id, String name, double alw) { repo.add(new Position(alw, name, id)); }
}
