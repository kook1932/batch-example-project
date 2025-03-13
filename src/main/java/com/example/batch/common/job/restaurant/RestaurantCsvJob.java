package com.example.batch.common.job.restaurant;

import com.example.batch.common.domain.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RestaurantCsvJob {

    private final DataSource dataSource;

    @Value("/Users/jeongyong/Downloads/fulldata_07_24_04_P_일반음식점.csv")
    private String inputFilePath;

    /**
     * Job 구성
     */
    @Bean
    public Job importRestaurantJob(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        log.info(">>>>> importRestaurantJob");
        return new JobBuilder("importRestaurantJob", jobRepository)
                .start(step1(null, transactionManager, jobRepository))
                .build();
    }

    /**
     * Step 생성 (chunk 기반, 1000건 단위)
     */
    @Bean
    @JobScope
    public Step step1(
            @Value("#{jobParameters[version]}") String version,
            PlatformTransactionManager transactionManager,
            JobRepository jobRepository
    ) {
        log.info(">>>>> version: {}", version);
        log.info(">>>>> step1");

        return new StepBuilder("step1", jobRepository)
                .<Restaurant, Restaurant>chunk(100, transactionManager)
                .reader(jdbcCursorItemReader())
                .writer(writer())
//                .faultTolerant() // 필요에 따라 재시도/건너뛰기 정책 추가 가능
                .build();
    }

//    /**
//     * CSV 파일을 읽어 Restaurant 객체로 매핑하는 Reader
//     */
//    @Bean
//    public FlatFileItemReader<Restaurant> reader1() {
//        log.info(">>>>> reader");
//        FlatFileItemReader<Restaurant> reader = new FlatFileItemReader<>();
//
//        // 외부 파일 경로는 inputFilePath 프로퍼티에서 주입받습니다.
//        reader.setResource(new FileSystemResource(inputFilePath));
//        reader.setLinesToSkip(1); // CSV 헤더 스킵
//
//        // TODO: 테스트 시 100행만 읽도록 설정 (헤더는 제외)
//        reader.setMaxItemCount(101);
//
//        DefaultLineMapper<Restaurant> lineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setDelimiter(",");
//        // CSV 헤더 순서에 맞게 필드명 지정
//        tokenizer.setNames(Restaurant.getFieldNames());
//
//        BeanWrapperFieldSetMapper<Restaurant> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Restaurant.class);
//
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        reader.setLineMapper(lineMapper);
//        reader.setEncoding("EUC-KR");
//
//        return reader;
//    }

    @Bean
    public JdbcCursorItemReader<Restaurant> jdbcCursorItemReader() {
        log.info(">>>>> jdbcCursorItemReader");

//        log.info(">>>>> reader");
//        FlatFileItemReader<Restaurant> reader = new FlatFileItemReader<>();
//
//        // 외부 파일 경로는 inputFilePath 프로퍼티에서 주입받습니다.
//        reader.setResource(new FileSystemResource(inputFilePath));
//        reader.setLinesToSkip(1); // CSV 헤더 스킵
//
//        // TODO: 테스트 시 100행만 읽도록 설정 (헤더는 제외)
//        reader.setMaxItemCount(101);
//
//        DefaultLineMapper<Restaurant> lineMapper = new DefaultLineMapper<>();
//
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setDelimiter(",");
//        // CSV 헤더 순서에 맞게 필드명 지정
//        tokenizer.setNames(Restaurant.getFieldNames());
//
//        BeanWrapperFieldSetMapper<Restaurant> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Restaurant.class);
//
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        reader.setLineMapper(lineMapper);
//        reader.setEncoding("EUC-KR");

        return new JdbcCursorItemReaderBuilder<Restaurant>()
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Restaurant.class))
                .sql("SELECT * FROM restaurant")
                .name("jdbcCursorItemReader")
                .build();
    }

    /**
     * JdbcBatchItemWriter 설정 - H2 메모리 DB의 restaurant 테이블에 데이터를 INSERT합니다.
     */
    @Bean
    public JdbcBatchItemWriter<Restaurant> writer() {
        log.info(">>>>> writer");
//        JdbcBatchItemWriter<Restaurant> writer = new JdbcBatchItemWriter<>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        writer.setSql("INSERT INTO restaurant (number, service_name, service_id, local_government_code, management_number) " +
//                "VALUES (:number, :serviceName, :serviceId, :localGovernmentCode, :managementNumber)");
//        writer.setDataSource(dataSource);
//        return writer;

        return new JdbcBatchItemWriterBuilder<Restaurant>()
                .dataSource(dataSource)
                .sql("INSERT INTO restaurant (number) VALUES (:number)"
//                        "INSERT INTO restaurant (" +
//                                "number, service_name, service_id, local_government_code, management_number, " +
//                                "license_date, license_cancel_date, business_status_code, business_status_name, " +
//                                "detail_business_status_code, detail_business_status_name, closure_date, " +
//                                "temporary_closure_start_date, temporary_closure_end_date, reopen_date, address_phone, " +
//                                "address_area, address_zipcode, full_address, road_address, road_zipcode, " +
//                                "company_name, last_modified, data_update_type, data_update_date, business_type_name, " +
//                                "coordinate_x, coordinate_y, hygiene_business_type_name, male_employee_count, " +
//                                "female_employee_count, surrounding_business_status_name, grade_classification_name, " +
//                                "water_supply_facility_name, total_employee_count, head_office_employee_count, " +
//                                "factory_office_employee_count, factory_sales_employee_count, factory_production_employee_count, " +
//                                "building_ownership_type, deposit_amount, monthly_rent, multi_use_business_flag, " +
//                                "facility_total_scale, traditional_business_number, traditional_business_main_food, homepage" +
//                                ") VALUES (" +
//                                ":number, :serviceName, :serviceId, :localGovernmentCode, :managementNumber, " +
//                                ":licenseDate, :licenseCancelDate, :businessStatusCode, :businessStatusName, " +
//                                ":detailBusinessStatusCode, :detailBusinessStatusName, :closureDate, " +
//                                ":temporaryClosureStartDate, :temporaryClosureEndDate, :reopenDate, :addressPhone, " +
//                                ":addressArea, :addressZipcode, :fullAddress, :roadAddress, :roadZipcode, " +
//                                ":companyName, :lastModified, :dataUpdateType, :dataUpdateDate, :businessTypeName, " +
//                                ":coordinateX, :coordinateY, :hygieneBusinessTypeName, :maleEmployeeCount, " +
//                                ":femaleEmployeeCount, :surroundingBusinessStatusName, :gradeClassificationName, " +
//                                ":waterSupplyFacilityName, :totalEmployeeCount, :headOfficeEmployeeCount, " +
//                                ":factoryOfficeEmployeeCount, :factorySalesEmployeeCount, :factoryProductionEmployeeCount, " +
//                                ":buildingOwnershipType, :depositAmount, :monthlyRent, :multiUseBusinessFlag, " +
//                                ":facilityTotalScale, :traditionalBusinessNumber, :traditionalBusinessMainFood, :homepage" +
//                                ")"
                )
                .beanMapped()
                .build();
    }

}
