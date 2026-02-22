package br.com.swetonyancelmo.controllers.docs;

import br.com.swetonyancelmo.data.dto.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File Endpoint")
public interface FileControlerDocs {

    UploadFileResponseDTO uploadFile(MultipartFile file);
    List<UploadFileResponseDTO> uploadMultipleFiles(MultipartFile[] files);
    ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest httpServletRequest);
}
