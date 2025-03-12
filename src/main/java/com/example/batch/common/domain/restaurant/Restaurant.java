package com.example.batch.common.domain.restaurant;

import lombok.Data;

@Data
public class Restaurant {

    // 1. 기본 정보
    private String number;                         // 번호
    private String serviceName;                    // 개방서비스명
    private String serviceId;                      // 개방서비스아이디
    private String localGovernmentCode;            // 개방자치단체코드
    private String managementNumber;               // 관리번호

    // 2. 인허가 관련
    private String licenseDate;                    // 인허가일자
    private String licenseCancelDate;              // 인허가취소일자

    // 3. 영업상태 관련
    private String businessStatusCode;             // 영업상태구분코드
    private String businessStatusName;             // 영업상태명
    private String detailBusinessStatusCode;       // 상세영업상태코드
    private String detailBusinessStatusName;       // 상세영업상태명

    // 4. 폐업 및 휴업 관련
    private String closureDate;                    // 폐업일자
    private String temporaryClosureStartDate;      // 휴업시작일자
    private String temporaryClosureEndDate;        // 휴업종료일자
    private String reopenDate;                     // 재개업일자

    // 5. 소재지 정보
    private String addressPhone;                   // 소재지전화
    private String addressArea;                    // 소재지면적
    private String addressZipcode;                 // 소재지우편번호
    private String fullAddress;                    // 소재지전체주소

    // 6. 도로명 주소 정보
    private String roadAddress;                    // 도로명전체주소
    private String roadZipcode;                    // 도로명우편번호

    // 7. 사업장 및 수정 정보
    private String companyName;                    // 사업장명
    private String lastModified;                   // 최종수정시점
    private String dataUpdateType;                 // 데이터갱신구분
    private String dataUpdateDate;                 // 데이터갱신일자

    // 8. 업태 및 좌표 정보
    private String businessTypeName;               // 업태구분명
    private String coordinateX;                    // 좌표정보x(epsg5174)
    private String coordinateY;                    // 좌표정보y(epsg5174)
    private String hygieneBusinessTypeName;        // 위생업태명

    // 9. 종사자 정보
    private String maleEmployeeCount;              // 남성종사자수
    private String femaleEmployeeCount;            // 여성종사자수

    // 10. 영업장 주변, 등급, 급수시설 정보
    private String surroundingBusinessStatusName;  // 영업장주변구분명
    private String gradeClassificationName;        // 등급구분명
    private String waterSupplyFacilityName;        // 급수시설구분명

    // 11. 직원 및 사업장 규모 정보
    private String totalEmployeeCount;             // 총직원수
    private String headOfficeEmployeeCount;        // 본사직원수
    private String factoryOfficeEmployeeCount;     // 공장사무직직원수
    private String factorySalesEmployeeCount;      // 공장판매직직원수
    private String factoryProductionEmployeeCount; // 공장생산직직원수

    // 12. 건물, 임대 관련 정보
    private String buildingOwnershipType;          // 건물소유구분명
    private String depositAmount;                  // 보증액
    private String monthlyRent;                    // 월세액

    // 13. 추가 정보
    private String multiUseBusinessFlag;           // 다중이용업소여부
    private String facilityTotalScale;             // 시설총규모
    private String traditionalBusinessNumber;      // 전통업소지정번호
    private String traditionalBusinessMainFood;    // 전통업소주된음식
    private String homepage;                       // 홈페이지

    // CSV 파일의 헤더(필드명) 순서를 반환하는 메서드
    public static String[] getFieldNames() {
        return new String[]{
                "number", "serviceName", "serviceId", "localGovernmentCode", "managementNumber",
                "licenseDate", "licenseCancelDate", "businessStatusCode", "businessStatusName",
                "detailBusinessStatusCode", "detailBusinessStatusName", "closureDate",
                "temporaryClosureStartDate", "temporaryClosureEndDate", "reopenDate", "addressPhone",
                "addressArea", "addressZipcode", "fullAddress", "roadAddress", "roadZipcode",
                "companyName", "lastModified", "dataUpdateType", "dataUpdateDate", "businessTypeName",
                "coordinateX", "coordinateY", "hygieneBusinessTypeName", "maleEmployeeCount",
                "femaleEmployeeCount", "surroundingBusinessStatusName", "gradeClassificationName",
                "waterSupplyFacilityName", "totalEmployeeCount", "headOfficeEmployeeCount",
                "factoryOfficeEmployeeCount", "factorySalesEmployeeCount", "factoryProductionEmployeeCount",
                "buildingOwnershipType", "depositAmount", "monthlyRent", "multiUseBusinessFlag",
                "facilityTotalScale", "traditionalBusinessNumber", "traditionalBusinessMainFood", "homepage"
        };
    }

}
