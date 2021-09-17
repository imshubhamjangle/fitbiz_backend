package auth.service;

import auth.dto.UserResponseDTO;
import auth.exception.CustomException;
import auth.model.Workout;
import auth.repository.WorkoutLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutLogService {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WorkoutLogRepository workoutLogRepository;

    public List<Workout> getAllWorkoutDataByUserId(HttpServletRequest req) {
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        List<Workout> workoutDataList = workoutLogRepository.findAll();
        return workoutDataList.stream().filter(e -> Objects.equals(e.getUserId(), userId)).collect(Collectors.toList());
    }

    public void addWorkout(HttpServletRequest req, Workout workout){
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        workout.setUserId(userId);
        workoutLogRepository.save(workout);
    }

    public void updateWorkoutById(HttpServletRequest req, Workout workout){
        System.out.println("In updateWorkoutById");
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        Optional<Workout> os = workoutLogRepository.findById(workout.getId());
        if(os.isPresent()){
            //System.out.println("Workout is present -- " + os.get().getUserId() + " == " + userId);
            if(Objects.equals(os.get().getUserId(), userId)){
                System.out.println("Updating workout");
                workout.setUserId(userId);
                workoutLogRepository.save(workout);
            }
        } else {
            throw new CustomException("No such workout exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deleteWorkoutById(HttpServletRequest req, Integer workoutId){
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        Optional<Workout> os = workoutLogRepository.findById(workoutId);

        if(os.isPresent()){
            if(Objects.equals(os.get().getUserId(), userId)){
                workoutLogRepository.deleteById(workoutId);
            }
        } else {
            throw new CustomException("No such workout exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
