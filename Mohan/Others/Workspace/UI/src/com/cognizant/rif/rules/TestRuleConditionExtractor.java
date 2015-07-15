package com.cognizant.rif.rules;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.rif.constants.RIFInputConstants;
import com.cognizant.rif.vo.RuleReportVO;

public class TestRuleConditionExtractor {

	public TestRuleConditionExtractor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sourceCodepath=RIFInputConstants.SOURCE_CODE_ROOT_DIR+
				"provider\\providerenrollment\\ProviderEnrollmentPortlet\\JavaSource\\com\\acs\\enterprise\\mmis\\provider\\enrollment\\view\\helper";
		List<String> ruleIDList=new ArrayList<>();
		RuleReportVO ruleRepVo=new RuleReportVO();
		File file=new File(("D:\\"+RIFInputConstants.RULE_EXTRACTION_FOLDER+"\\"));
		file.mkdirs();
		try {
			RuleConditionsExtractor.extractAllDataKeyWordSearch(sourceCodepath, file.getAbsolutePath(),ruleIDList,ruleRepVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
