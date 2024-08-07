package com.edu.nlu.document.config;

import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.repository.DocumentRepository;
import com.edu.nlu.document.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Configuration
public class SchemaLoader {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
//            createPermissionsAndRoles();
            createUsers();
            createDocuments();
        };
    }

    private void createDocuments() {
        Random random = new Random();
        Document doc1 = Document.builder()
                .id(1L)
                .soDen("HSK")
                .soKiHieuVanBan("Công văn")
                .coQuanBanHanh("Hội Sân khấu TPHCM")
                .loaiVanBan("Về việc báo cáo đổi mới cơ chế quản lý, tổ chức lại hệ thống đơn vị sự nghiệp công lập và kết quả thực hiện Nghị quyết số 19-NQ/TW")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();

        Document doc2 = Document.builder()
                .id(2L)
                .soDen("TM-TCCS")
                .soKiHieuVanBan("Thư mời")
                .coQuanBanHanh("Tạp chí cộng sản")
                .loaiVanBan("Viết tham luận Hội thảo khoa học")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();

        Document doc3 = Document.builder()
                .id(3L)
                .soDen("001/2022/TB-MCG")
                .soKiHieuVanBan("Thông báo")
                .coQuanBanHanh("Công ty Cổ phần Giáo dục MCG Việt Nam")
                .loaiVanBan("V/v tuyển sinh các lớp đào tạo bồi dưỡng kiến thức QLNN")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();

        Document doc4 = Document.builder()
                .id(4L)
                .soDen("008/2022/CV-MCG")
                .soKiHieuVanBan("Công văn")
                .coQuanBanHanh("Công ty Cổ phần Giáo dục MCG Việt Nam")
                .loaiVanBan("V/v phối hợp khảo sát, tuyển sinh các chương trình bồi dưỡng, đào tạo cho CBCC, VC và NLĐ của cơ quan, đơn vị")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();

        Document doc5 = Document.builder()
                .id(5L)
                .soDen("01-CV/HVCB")
                .soKiHieuVanBan("Công văn")
                .coQuanBanHanh("Học viện Cán bộ - TPHCM")
                .loaiVanBan("Về kinh phí lớp Trung cấp lý luận chính trị năm 2022 của Sở Nội vụ")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();

        Document doc6 = Document.builder()
                .id(6L)
                .soDen("01/BC-NV")
                .soKiHieuVanBan("Báo cáo")
                .coQuanBanHanh("Phòng Nội vụ - Quận Tân Bình - TPHCM")
                .loaiVanBan("Báo cáo danh sách cán bộ công chức phòng Nội vụ Quý I năm 2022")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDateTime.now())
                .trichYeu(String.valueOf(random.nextInt()))
                .build();


        documentRepository.saveAll(List.of(doc1,doc2,doc3,doc4,doc5,doc6));

    }


    void createUsers() {

        // Create and persist Users
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setName("User One");
//        user1.setRoles(new HashSet<>(Set.of(userRole)));

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setName("User Two");
//        user2.setRoles(new HashSet<>(Set.of(userRole)));

        User user3 = new User();
        user3.setUsername("user3");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setName("User Three");
//        user3.setRoles(new HashSet<>(Set.of(userRole)));

        User user4 = new User();
        user4.setUsername("user4");
        user4.setPassword(passwordEncoder.encode("password"));
        user4.setName("User Four");
//        user4.setRoles(new HashSet<>(Set.of(userRole)));

        User user5 = new User();
        user5.setUsername("user5");
        user5.setPassword(passwordEncoder.encode("password"));
        user5.setName("User Five");
//        user5.setRoles(new HashSet<>(Set.of(adminRole)));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
    }
}
