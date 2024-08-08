package com.edu.nlu.document.config;

import com.edu.nlu.document.enums.Role;
import com.edu.nlu.document.enums.Status;
import com.edu.nlu.document.model.Document;
import com.edu.nlu.document.model.Statement;
import com.edu.nlu.document.model.User;
import com.edu.nlu.document.repository.DocumentRepository;
import com.edu.nlu.document.repository.StatementRepository;
import com.edu.nlu.document.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Configuration
public class SchemaLoader {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatementRepository statementRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
//            createPermissionsAndRoles();
            createUsers();
            createDocuments();
            createStatements();
        };
    }

    private void createDocuments() {
        Random random = new Random();
        Document doc1 = Document.builder()
                .id(1L)
                .soVanBan("1002/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("01/KH-UBND")
                .coQuanBanHanh("Ủy ban nhân dân Quận 3 TP.HCM")
                .loaiVanBan("Kế hoạch")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.now())
                .trichYeu("Tổ chức Hội nghị triển khai nhiệm vụ phát triển kinh tế xã hội năm 2022 chủ đề Thích ứng an toàn, linh hoạt kiểm soát hiệu quả dịch covid-19, tiếp tục nâng cao chất lượng xây dựng đô thị, cải thiện môi trường đầu tư đẩy mạnh cải cách hành chính và đồng hành cùng doanh nghiệp")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Kinh tế")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc2 = Document.builder()
                .id(2L)
                .soVanBan("1003/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/BC-BDT")
                .coQuanBanHanh("Ban Dân tộc - TPHCM")
                .loaiVanBan("Báo cáo")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 6, 1))
                .trichYeu("Báo cáo sơ kết 01 năm thực hiện Chương trình cải cách hành chính và giải pháp nâng cao Chỉ số cải cách hành chính giai đoạn 2020-2025 tại Ban Dân tộc Thành phố")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc3 = Document.builder()
                .id(3L)
                .soVanBan("1004/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/BC-SNN")
                .coQuanBanHanh("Sở Nông nghiệp và Phát triển nông thôn TP.HCM")
                .loaiVanBan("Báo cáo")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 5, 1))
                .trichYeu("Báo cáo sơ kết 01 năm thực hiện Chương trình cải cách hành chính (viết tắt là CCHC) và giải pháp nâng cao Chỉ số cải cách hành chính trên địa bàn Thành phố giai đoạn 2020 - 2025")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc4 = Document.builder()
                .id(4L)
                .soVanBan("1005/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/BC-TTTP")
                .coQuanBanHanh("Thanh tra Thành phố - TPHCM")
                .loaiVanBan("Báo cáo")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 7, 1))
                .trichYeu("Báo cáo sơ kết 01 năm thực hiện Chương trình cải cách hành chính và giải pháp nâng cao chỉ số cải cách hành chính giai đoạn 2020-2025 tại Thanh tra Thành phố")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc5 = Document.builder()
                .id(5L)
                .soVanBan("1006/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/BC-UBND")
                .coQuanBanHanh("Ủy ban nhân dân Quận Phú Nhuận TP.HCM")
                .loaiVanBan("Báo cáo")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 7, 6))
                .trichYeu("Báo cáo kết quả công tác CCHC trên địa bàn quận Phú Nhuận giai đoạn 2020-2025")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc6 = Document.builder()
                .id(6L)
                .soVanBan("1007/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/CT-UBND")
                .coQuanBanHanh("Ủy ban nhân dân Quận Tân Phú TP.HCM")
                .loaiVanBan("Chỉ thị")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 5, 31))
                .trichYeu("V/v lập danh sách điều tra xã hội học phục vụ xác định Chỉ số Cải cách hành chính năm 2021 của Thành phố")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();

        Document doc7 = Document.builder()
                .id(7L)
                .soVanBan("1008/SNV")
                .soDen("HSK")
                .soKiHieuVanBan("02/UBND")
                .coQuanBanHanh("Ủy ban nhân dân Quận Phú Nhuận TP.HCM")
                .loaiVanBan("Công văn")
                .ngayDen(LocalDateTime.now())
                .ngayBanHanh(LocalDate.of(2022, 5, 1))
                .trichYeu("V/v điều tra xã hội học phục vụ xác định Chỉ số Cải cách hành chính năm 2021 của Thành phố")
                .coBangGiay(true)
                .thoiGianTao(LocalDate.now())
                .thoiGianXuLy(LocalDate.now())
                .vanBanChiDao(true)
                .vanBanQPPL(true)
                .capCoQuan("Cơ quan xử lý")
                .linhVuc("Cải cách hành chính")
                .ghiChu("Xử lý gấp")
                .build();


        documentRepository.saveAll(List.of(doc1,doc2,doc3,doc4,doc5,doc6, doc7));

    }


    void createUsers() {

        // Create and persist Users
        User user1 = new User();
        user1.setUsername("user1");
        user1.setRole(Role.VAN_THU);
        user1.setPosition("Văn thư");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setName("Văn thư");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setRole(Role.CHANH_VAN_PHONG);
        user2.setPosition("Chánh Văn Phòng");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setName("Chánh Văn Phòng");

        User user3 = new User();
        user3.setUsername("user3");
        user3.setRole(Role.CHANH_VAN_PHONG);
        user3.setPosition("Chuyên viên");
        user3.setPassword(passwordEncoder.encode("password"));
        user3.setName("Lê Mộng Trang");

        User user4 = new User();
        user4.setUsername("user4");
        user4.setRole(Role.BAN_GIAM_DOC);
        user4.setPosition("Giám đốc");
        user4.setPassword(passwordEncoder.encode("password"));
        user4.setName("Huỳnh Thanh Nhân");

        User user5 = new User();
        user5.setUsername("user5");
        user5.setRole(Role.BAN_GIAM_DOC);
        user5.setPosition("Phó Giám Đốc");
        user5.setPassword(passwordEncoder.encode("password"));
        user5.setName("Nguyễn Bắc Nam");

        User user6 = new User();
        user6.setUsername("user6");
        user6.setRole(Role.BAN_GIAM_DOC);
        user6.setPosition("Phó Giám Đốc");
        user6.setPassword(passwordEncoder.encode("password"));
        user6.setName("Phan Kiều Thanh Hương");

        User user7 = new User();
        user7.setUsername("user7");
        user7.setRole(Role.BAN_GIAM_DOC);
        user7.setPosition("Phó Giám Đốc");
        user7.setPassword(passwordEncoder.encode("password"));
        user7.setName("Nguyễn Thị Hồng Thắm");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
    }

    void createStatements(){
        Statement statement1 = new Statement();
        statement1.setId(1L);
        statement1.setStatus(Status.CREATED);
        statement1.setUserId(1L);
        statement1.setDocumentId(1L);

        Statement statement2 = new Statement();
        statement2.setId(2L);
        statement2.setStatus(Status.CREATED);
        statement2.setUserId(1L);
        statement2.setDocumentId(2L);

        Statement statement3 = new Statement();
        statement3.setId(3L);
        statement3.setStatus(Status.CREATED);
        statement3.setUserId(1L);
        statement3.setDocumentId(3L);

        Statement statement4 = new Statement();
        statement4.setId(4L);
        statement4.setStatus(Status.CREATED);
        statement4.setUserId(1L);
        statement4.setDocumentId(4L);

        Statement statement5 = new Statement();
        statement5.setId(5L);
        statement5.setStatus(Status.CREATED);
        statement5.setUserId(1L);
        statement5.setDocumentId(5L);

        Statement statement6 = new Statement();
        statement6.setId(6L);
        statement6.setStatus(Status.CREATED);
        statement6.setUserId(1L);
        statement6.setDocumentId(6L);

        Statement statement7 = new Statement();
        statement7.setId(7L);
        statement7.setStatus(Status.CREATED);
        statement7.setUserId(1L);
        statement7.setDocumentId(7L);

        Statement statement8 = new Statement();
        statement8.setId(1L);
        statement8.setStatus(Status.FORWARDED);
        statement8.setUserId(1L);
        statement8.setDocumentId(1L);

        Statement statement9 = new Statement();
        statement9.setId(8L);
        statement9.setStatus(Status.SENT);
        statement9.setUserId(2L);
        statement9.setDocumentId(1L);

        Statement statement10 = new Statement();
        statement10.setId(9L);
        statement10.setStatus(Status.RECEIVED);
        statement10.setUserId(3L);
        statement10.setDocumentId(1L);

        statementRepository.save(statement1);
        statementRepository.save(statement2);
        statementRepository.save(statement3);
        statementRepository.save(statement4);
        statementRepository.save(statement5);
        statementRepository.save(statement6);
        statementRepository.save(statement7);
        statementRepository.save(statement8);
        statementRepository.save(statement9);
        statementRepository.save(statement10);
    }
}
