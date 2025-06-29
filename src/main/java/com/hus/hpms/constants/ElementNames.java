package com.hus.hpms.constants;

public interface ElementNames
{
    public String[] requestKeys = {
            "collegeNames",
            "areaNames",
            "detailOneSelectNames",
            "detailTwoSelectNames",
            "detailThreeSelectNames",
            "detailFourSelectNames",
            "detailFiveSelectNames"
    };

    public String[] collegeNamesEng = {
            "theologicalCollegeSelect",
            "humanitiesAndSocialSciencesCollege",
            "businessManagementCollege",
            "techCollege",
            "artCollege",
            "wesleyCollege"
    };

    public String[] collegeNamesKor = {
            "신학 대학",
            "인문 사회 과학 대학",
            "경영 대학",
            "이공 대학",
            "예술 대학",
            "웨슬리 창의 융합 대학"
    };

    public String[] areaNames = {
            "1 영역",
            "2 영역",
            "3 영역",
            "4 영역",
            "5 영역"
    };

    public String[] detailOneSelectNames = {
            "1.1. 교육 목표 및 인재상",
            "1.2. 발전 계획",
            "1.3. 거버넌스",
            "1.4. 재정 확보",
            "1.5. 재정 집행",
            "1.6. 감사"
    };

    public String[] detailTwoSelectNames = {
            "2.1. 교육 과정 체계",
            "2.2. 교양 교육 과정",
            "2.3. 전공 교육 과정",
            "2.4. 학사 관리",
            "2.5. 수업",
            "2.6. 교수-학습 지원"
    };

    public String[] detailThreeSelectNames = {
            "3.1. 교원 처우 및 복지",
            "3.2. 교원 인사 및 업적 평가",
            "3.3. 교원 처우 및 복지",
            "3.4. 교원의 교육 및 연구활동 지원",
            "3.5. 직원 확보 및 인사",
            "3.6. 직원 복지 및 업무 역량 개발 지원"
    };

    public String[] detailFourSelectNames = {
            "4.1. 장학 제도 및 학생 자치 활동 지원",
            "4.2. 학생 심리 및 진로 상담",
            "4.3. 학생 권익 보호 및 소수 집단 학생 지원",
            "4.4. 교육 시설",
            "4.5. 기숙사 및 학생 복지 시설",
            "4.6. 도서관"
    };

    public String[] detailFiveSelectNames = {
            "5.1. 성과 관리",
            "5.2. 교육 성과",
            "5.3. 연구 성과",
            "5.4. 취창업 지원 평가",
            "5.5. 사회 봉사",
            "5.6. 지역 사회 연계 협력"
    };

    public String[][] requestValues = {
            collegeNamesKor,
            areaNames,
            detailOneSelectNames,
            detailTwoSelectNames,
            detailThreeSelectNames,
            detailFourSelectNames,
            detailFiveSelectNames
    };
}
