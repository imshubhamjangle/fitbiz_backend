package auth.repository;

import auth.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutLogRepository extends JpaRepository<Workout, Integer> {
}
