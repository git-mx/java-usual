package com.shyfay.usual;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("agency_id", "AAAA");
        dataMap.put("agency_name", "BBBB");
        dataMap.put("org_code", "CCCC");
        dataMap.put("address", "DDDD");
        dataMap.put("longitude", "23.1111111");
        dataMap.put("latitude", "113.1111111");
        String agencyId = dataMap.get("agency_id");
        String agencyName = dataMap.get("agency_name");
        String orgCode = dataMap.get("org_code");
        String orgId = orgCode;
        String address = dataMap.get("address");
        String longitude = dataMap.get("longitude");
        String latitude = dataMap.get("latitude");
        StringBuilder stringBuilder = new StringBuilder("insert into s_org_ext(ROW_ID,CREATED,CREATED_BY,LAST_UPD,LAST_UPD_BY,DCKING_NUM, \n");
        stringBuilder.append("MODIFICATION_NUM,CONFLICT_ID,PAR_ROW_ID,ACCNT_FLG,ACTIVE_FLG,BUYING_GROUP_FLG,  \n");
        stringBuilder.append("BU_ID,CG_DEDN_AUTH_FLG,CG_SVP_A_LOCK_FLG,CG_SVP_LOCK_FLG,CG_SVP_SKIP_FLG,CL_SITE_FLG,  \n");
        stringBuilder.append("CONTRACT_VIS_FLG,COURT_PAY_FLG,DISA_CLEANSE_FLG,EVT_LOC_FLG,EXTERNAL_HOST_FLG,FCST_ORG_FLG,  \n");
        stringBuilder.append("FUND_ELIG_FLG,GOOD_STANDING_FLG,INCL_FLG,INT_ORG_FLG,NAME,ONDEMAND_SYNC_FLG,  \n");
        stringBuilder.append("PLAN_GROUP_FLG,PROSPECT_FLG,PRTNR_FLG,PRTNR_PUBLISH_FLG,RPLCD_WTH_CMPT_FLG,SKIP_PO_CRDCHK_FLG,  \n");
        stringBuilder.append("AGENCY_FLG,BRANCH_FLG,BRIEFING_LAYOUT,CLIENT_FLG,ENTERPRISE_FLAG,FACILITY_FLG,  \n");
        stringBuilder.append("INVSTR_FLG,LANG_ID,PR_REP_DNRM_FLG,PR_REP_MANL_FLG,PR_REP_SYS_FLG,  \n");
        stringBuilder.append("ASGN_USR_EXCLD_FLG,DB_LAST_UPD,ABA_NUMBER,BASE_CURCY_CD,DB_LAST_UPD_SRC,DIVN_TYPE_CD, \n");
        stringBuilder.append("PAR_BU_ID,PR_ADDR_ID,DIRECTIONS,X_ACCOUNT_ATTRI,X_LATITUDE,X_LONGITUDE) \n");
        stringBuilder.append("values ('").append(agencyId).append("'");
        stringBuilder.append(", sysdate");
        stringBuilder.append(", 'BBBB'");
        stringBuilder.append(", sysdate");
        stringBuilder.append(", 'BBBB'");
        stringBuilder.append(", 0");
        stringBuilder.append(", 0");
        stringBuilder.append(", 0");
        stringBuilder.append(", '").append(agencyId).append("'");
        stringBuilder.append(", 'Y', 'Y', 'N'");
        stringBuilder.append(", '0-R9NH'");
        stringBuilder.append(", 'Y', 'N', 'N', 'N', 'N'");
        stringBuilder.append(", 'N', 'N', 'N', 'N', 'N', 'N'");
        stringBuilder.append(", 'N', 'N', 'N', 'Y'");
        stringBuilder.append(", '").append(agencyName).append("'");
        stringBuilder.append(", 'N'");
        stringBuilder.append(", 'N', 'N', 'N', 'N', 'N', 'N'");
        stringBuilder.append(", 'N', 'N', 'Default', 'N', 'N', 'N'");
        stringBuilder.append(", 'N', 'ENU', 'N', 'N', 'N', 'Y'");
        stringBuilder.append(", sysdate");
        if(StringUtils.isNotBlank(address)){
            stringBuilder.append(", '").append(address).append("'");
        }else{
            stringBuilder.append(", ''");
        }
        stringBuilder.append(", 'CNY'");
        stringBuilder.append(", 'User'");
        stringBuilder.append(", '办事处'");
        stringBuilder.append(", '").append(orgId).append("'");
        stringBuilder.append(", 'No Match Row Id'");
        stringBuilder.append(", '<Long>'");
        stringBuilder.append(", '").append(agencyName).append("'");
        if(StringUtils.isNotBlank(latitude)){
            stringBuilder.append(", ").append(latitude);
        }else{
            stringBuilder.append(", ").append("NULL");
        }
        if(StringUtils.isNotBlank(longitude)){
            stringBuilder.append(", ").append(longitude);
        }else{
            stringBuilder.append(", ").append("NULL");
        }
        stringBuilder.append(")");
        String exeSql = stringBuilder.toString();
        System.out.println(exeSql);
    }
}
