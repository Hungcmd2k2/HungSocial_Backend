package com.HungSocial.Server.Service.User;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.DTO.User.SearchUserDTO;
import com.HungSocial.Server.DTO.User.UserOtherInfor;
import com.HungSocial.Server.DTO.UserDetail.UpdateAvatarDTO;
import com.HungSocial.Server.DTO.UserDetail.UserDetailDTO;
import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Entity.UserDetails.UserDetails;
import com.HungSocial.Server.Repository.User.UserRepository;
import com.HungSocial.Server.Repository.UserDetails.UserDetailRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;
     @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(Integer id, String password) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
        User user_update = user.get();
        String encodedPassword = passwordEncoder.encode(password);
           user_update.setPassword(encodedPassword);
           return   userRepository.save(user_update);
          
        }
        else{
            return null;
        }

    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);                       
    }
    public Optional<User> getUserById(Integer userId){
        return userRepository.findById(userId);
    }
    public Optional<User>getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Tìm user theo keyword
    public List<SearchUserDTO> getUserByKeyWord(String keyword){
        return userRepository.findByUsernameContaining(keyword);
    }

// ----------------- UserDetail---------------------


public UserDetails updateUserDetail(UserDetailDTO userDetailDTO){
    Optional<UserDetails> userDetailOptional = userDetailRepository.findByUserid(userDetailDTO.getUserid());
    if(userDetailOptional.isPresent()){
        UserDetails userDetails =userDetailOptional.get();
        userDetails.setFullname(userDetailDTO.getFullname());
        userDetails.setDob(userDetailDTO.getDob());
        userDetails.setLinksocial(userDetailDTO.getLinksocial());
        userDetails.setLovesong(userDetailDTO.getLovesong());
        userDetails.setEducation(userDetailDTO.getEducation());
        userDetails.setAddress(userDetailDTO.getAddress());
        userDetailRepository.save(userDetails);
        return userDetails;
    }else{
        return null;
    }
}
//Update avatar cho detail user
public UserDetails updateAvater(UpdateAvatarDTO updateAvatar){
    Optional<UserDetails> userDetailOptional = userDetailRepository.findByUserid(updateAvatar.getUserid());
    if(userDetailOptional.isPresent()){
        UserDetails userDetails =userDetailOptional.get();

       userDetails.setAvatar(updateAvatar.getFilepath());
        userDetailRepository.save(userDetails);
        return userDetails;
    }else{
        return null;
    }
}
//Update ảnh bìa cho user
public UserDetails updateCoverPhoto(UpdateAvatarDTO updateAvatar){
    Optional<UserDetails> userDetailOptional = userDetailRepository.findByUserid(updateAvatar.getUserid());
    if(userDetailOptional.isPresent()){
        UserDetails userDetails =userDetailOptional.get();

       userDetails.setCoverphoto(updateAvatar.getFilepath());
        userDetailRepository.save(userDetails);
        return userDetails;
    }else{
        return null;
    }
}

    

public Optional<UserDetails> getUserDetail(Integer userid){
    return userDetailRepository.findByUserid(userid);
}
   
// Lấy thông tin về User khác ( trang cá nhân)

public UserOtherInfor getUserOtherInfor(String username){
    Optional<User> user = userRepository.findByUsername(username);
    if(user.isPresent()){
        User getUserDTO = user.get();
       Integer  useridDTO = getUserDTO.getId();
       String  emailDTO = getUserDTO.getEmail();

       Optional<UserDetails> userDetails = userDetailRepository.findById(useridDTO);

       if(userDetails.isPresent()){
        UserDetails userDetailsDTO = userDetails.get();

        UserOtherInfor userOtherInfor = new UserOtherInfor();
        userOtherInfor.setUserid(userDetailsDTO.getUserid());
        userOtherInfor.setUsername(username);
        userOtherInfor.setEmail(emailDTO);
        userOtherInfor.setFullname(userDetailsDTO.getFullname());
        userOtherInfor.setAvatar(userDetailsDTO.getAvatar());
        userOtherInfor.setCoverphoto(userDetailsDTO.getCoverphoto());
        userOtherInfor.setDob(userDetailsDTO.getDob());
        userOtherInfor.setLinksocial(userDetailsDTO.getLinksocial());
        userOtherInfor.setLovesong(userDetailsDTO.getLovesong());
        userOtherInfor.setEducation(userDetailsDTO.getEducation());
        userOtherInfor.setAddress(userDetailsDTO.getAddress());

        return userOtherInfor;
       }
       else{
        return null;
       }
    }
    else{
        return null;
    }
}

   
}
