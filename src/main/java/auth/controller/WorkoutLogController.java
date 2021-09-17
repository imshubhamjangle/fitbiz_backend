package auth.controller;

import auth.model.Workout;
import auth.service.UserService;
import auth.service.WorkoutLogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WorkoutLogController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutLogService workoutLogService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/workout-log-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<Workout> getAllWorkoutDataByUserIdController(HttpServletRequest req) {
        return workoutLogService.getAllWorkoutDataByUserId(req);
    }

    @PostMapping("/workout-log-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void addWorkoutController(HttpServletRequest req, @RequestBody Workout workout){
        workoutLogService.addWorkout(req, workout);
    }

    @PutMapping("/workout-log-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void updateWorkoutByIdController(HttpServletRequest req, @RequestBody Workout workout){
        workoutLogService.updateWorkoutById(req, workout);
    }

    @DeleteMapping("/workout-log-user/{workoutId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void deleteWorkoutByIdController(HttpServletRequest req, @PathVariable Integer workoutId){
        workoutLogService.deleteWorkoutById(req, workoutId);
    }

}
