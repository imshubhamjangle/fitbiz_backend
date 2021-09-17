package auth.service;

import auth.dto.MealDataDTO;
import auth.dto.UserResponseDTO;
import auth.exception.CustomException;
import auth.model.MealData;
import auth.model.MealList;
import auth.repository.MealDataRepository;
import auth.repository.MealListRepository;
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
public class MealDataService {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MealDataRepository mealDataRepository;

    @Autowired
    private MealListRepository mealListRepository;

    public List<MealData> getAllMealDataByUserId(HttpServletRequest req) {
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        List<MealData> mealDataList = mealDataRepository.findAll();
        return mealDataList.stream().filter(e -> Objects.equals(e.getUserId(), userId)).collect(Collectors.toList());
    }

    public void addMealDataItem(HttpServletRequest req, MealDataDTO mealDataDTO) {
        MealData mealData = new MealData();

        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        Optional<MealList> mealList = mealListRepository.findById(mealDataDTO.getMealId());
        // error handling for userId and mealList

        mealData.setUserId(userId);
        mealData.setDate(mealDataDTO.getDate());

        if(mealList.isPresent()){
            mealData.setMealList(mealList.get());
            mealDataRepository.save(mealData);
        } else {
            throw new CustomException("No Meal Item found for given data", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public void deleteMealDataItem(HttpServletRequest req, Integer id) {
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        Optional<MealData> os = mealDataRepository.findById(id);
        if(os.isPresent()){
            if(Objects.equals(os.get().getUserId(), userId)) {
//                mealDataRepository.deleteById(id);
                mealDataRepository.deleteByMealDataUid(id);
            }
        } else  {
            throw new CustomException("No such meal exist for your id", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void updateMealDataItem(HttpServletRequest req, MealData mealData) {
        Integer userId = modelMapper.map(userService.whoami(req), UserResponseDTO.class).getId();
        Optional<MealData> os = mealDataRepository.findById(mealData.getUserId());
        if(os.isPresent()){
            if(Objects.equals(os.get().getUserId(), userId)){
                //We can update the db
                mealData.setUserId(userId);
                mealDataRepository.save(mealData);
            }
        } else {
            throw new CustomException("No such meal exist for your id", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
