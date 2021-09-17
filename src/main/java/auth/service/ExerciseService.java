package auth.service;

import auth.exception.CustomException;
import auth.model.Exercise;
import auth.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercise(){
        return exerciseRepository.findAll();
    }

    public List<Exercise> getOnlyNumberExercise() {
        Pageable firstPageTenRecords = PageRequest.of(0,8);
        return exerciseRepository.findAll(firstPageTenRecords).getContent();
    }

    public void addNewExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Integer id){
        try{
            exerciseRepository.deleteById(id);
        } catch (Exception e) {
            if(e instanceof EmptyResultDataAccessException) {
                throw new CustomException("No such exercise exist for given id", HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                System.out.println(e);
                throw new CustomException("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void updateExercise(Exercise exercise) {
        Optional<Exercise> os = exerciseRepository.findById(exercise.getId());
        if(os.isPresent()){
            exerciseRepository.save(exercise);
        } else {
            throw new CustomException("No such exercise exist for given id", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
