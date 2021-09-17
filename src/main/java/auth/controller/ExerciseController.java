package auth.controller;

import auth.model.Exercise;
import auth.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/exercise")
    public List<Exercise> getAllExerciseController(){
        return exerciseService.getAllExercise();
    }

    @GetMapping("/exercise/fetch-top8-only")
    public List<Exercise> getOnlyNumberExerciseController() {
        return exerciseService.getOnlyNumberExercise();
    }

    @PostMapping("/exercise")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addNewExerciseController(@RequestBody Exercise exercise){
        exerciseService.addNewExercise(exercise);
    }

    @DeleteMapping("/exercise/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteExerciseController(@PathVariable Integer id){
        exerciseService.deleteExercise(id);
    }

    @PutMapping("/exercise")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateExerciseController(@RequestBody Exercise exercise) {
        exerciseService.updateExercise(exercise);
    }


}
