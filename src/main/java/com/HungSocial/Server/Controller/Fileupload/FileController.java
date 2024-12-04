package com.HungSocial.Server.Controller.Fileupload;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.DTO.UserDetail.UpdateAvatarDTO;
import com.HungSocial.Server.Entity.File.FileEntity;
import com.HungSocial.Server.Entity.UserDetails.UserDetails;
import com.HungSocial.Server.Repository.File.FileRepository;
import com.HungSocial.Server.Service.File.FileService;
import com.HungSocial.Server.Service.Upload.FileStorageService;
import com.HungSocial.Server.Service.User.UserService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileRepository fileRepository;

    @Autowired

    private FileService fileService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Object>> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("postId") Integer postId) {
    
        // Kiểm tra nếu không có file nào được upload
        if (files == null || files.length == 0) {
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.BAD_REQUEST.value(),
                    "Không có tệp nào được tải lên",
                    null
            );
            return ResponseEntity.ok(response);
        }
    
        // Danh sách để lưu các thông tin file đã lưu
        List<FileEntity> fileEntities = new ArrayList<>();
    
        try {
            // Lặp qua từng file và xử lý
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue; // Bỏ qua tệp rỗng
                }
    
                // Lưu file vào hệ thống
                String urlPublic = fileStorageService.saveFile(file);
    
                // Lưu thông tin file vào cơ sở dữ liệu
                FileEntity fileEntity = new FileEntity();
                fileEntity.setPostId(postId);
                fileEntity.setFileName(file.getOriginalFilename());
                fileEntity.setFilePath(urlPublic);
                fileEntity.setUploadedAt(LocalDateTime.now().withSecond(0).withNano(0));
                fileRepository.save(fileEntity);
    
                // Thêm vào danh sách file đã lưu
                fileEntities.add(fileEntity);
            }
    
            // Kiểm tra nếu không lưu được file nào
            if (fileEntities.isEmpty()) {
                ApiResponse<Object> response = new ApiResponse<>(
                        "error",
                        HttpStatus.BAD_REQUEST.value(),
                        "Không có tệp hợp lệ được lưu",
                        null
                );
                return ResponseEntity.ok(response);
            }
    
            // Trả về thành công
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Các tệp đã được tải lên thành công",
                    fileEntities
            );
            return ResponseEntity.ok(response);
    
        } catch (Exception e) {
            // Xử lý ngoại lệ
            ApiResponse<Object> response = new ApiResponse<>(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Đã xảy ra lỗi trong quá trình xử lý tệp",
                    e.getMessage()
            );
            return ResponseEntity.ok(response);
        }
    }
    //Upadte avatar cho userr
    @PostMapping("/upload/AVATAR")
    public ResponseEntity<ApiResponse<Object>> uploadAvatar(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("userId") Integer userId) {
    
        // Kiểm tra nếu không có file nào được upload
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "error",
                    HttpStatus.BAD_REQUEST.value(),
                    "Không có tệp nào được tải lên",
                    null
            ));
        }
    
        try {
            UserDetails updatedUserDetails = null; // Để lưu thông tin avatar cập nhật
    
            // Lặp qua từng file và xử lý
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue; // Bỏ qua tệp rỗng
                }
    
                // Lưu file và lấy URL công khai
                String urlPublic = fileStorageService.saveFile(file);
    
                // Cập nhật avatar cho user
                UpdateAvatarDTO updateAvatar = new UpdateAvatarDTO(userId, urlPublic);
                updatedUserDetails = userService.updateAvater(updateAvatar);
    
                // Thoát vòng lặp sau khi cập nhật thành công (nếu chỉ xử lý 1 tệp)
                if (updatedUserDetails != null) {
                    break;
                }
            }
    
            // Kiểm tra nếu không có tệp nào hợp lệ hoặc cập nhật thất bại
            if (updatedUserDetails == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                        "error",
                        HttpStatus.BAD_REQUEST.value(),
                        "Không thể cập nhật avatar. Vui lòng thử lại.",
                        null
                ));
            }
    
            // Trả về kết quả thành công
            return ResponseEntity.ok(new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Cập nhật avatar thành công",
                    updatedUserDetails.getAvatar()
            ));
    
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Đã xảy ra lỗi trong quá trình xử lý tệp",
                    e.getMessage()
            ));
        }
    }
    //Update ảnh bìa
    @PostMapping("/upload/COVER-PHOTO")
    public ResponseEntity<ApiResponse<Object>> uploadCoverPhoto(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("userId") Integer userId) {
    
        // Kiểm tra nếu không có file nào được upload
        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "error",
                    HttpStatus.BAD_REQUEST.value(),
                    "Không có tệp nào được tải lên",
                    null
            ));
        }
    
        try {
            UserDetails updatedUserDetails = null; // Để lưu thông tin avatar cập nhật
    
            // Lặp qua từng file và xử lý
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue; // Bỏ qua tệp rỗng
                }
    
                // Lưu file và lấy URL công khai
                String urlPublic = fileStorageService.saveFile(file);
    
                // Cập nhật avatar cho user
                UpdateAvatarDTO updateAvatar = new UpdateAvatarDTO(userId, urlPublic);
                updatedUserDetails = userService.updateCoverPhoto(updateAvatar);
    
                // Thoát vòng lặp sau khi cập nhật thành công (nếu chỉ xử lý 1 tệp)
                if (updatedUserDetails != null) {
                    break;
                }
            }
    
            // Kiểm tra nếu không có tệp nào hợp lệ hoặc cập nhật thất bại
            if (updatedUserDetails == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                        "error",
                        HttpStatus.BAD_REQUEST.value(),
                        "Không thể cập nhật avatar. Vui lòng thử lại.",
                        null
                ));
            }
    
            // Trả về kết quả thành công
            return ResponseEntity.ok(new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Cập nhật avatar thành công",
                    updatedUserDetails.getCoverphoto()
            ));
    
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                    "error",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Đã xảy ra lỗi trong quá trình xử lý tệp",
                    e.getMessage()
            ));
        }
    }
   
    // Tìm kiếm file cho post id nếu có

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<Object>> getAllFileforPost(@PathVariable Integer postId){

        List<FileEntity> fileEntity = fileService.getAllFile(postId);

        if(!fileEntity.isEmpty()){
            ApiResponse<Object> response = new ApiResponse<>(
                    "success",
                    HttpStatus.OK.value(),
                    "Lấy về các  file trong post thành công",
                    fileEntity
            );
            return ResponseEntity.ok(response); 
        }else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ko lấy được file hoặc post ko có file hoặc chưa có post đó",
               null
        );
        return ResponseEntity.ok(response);
        }
    }
    


}
