package com.cognizant.rif.utilities;

import com.cognizant.rif.vo.RuleReportVO;

public class ValidatorUtil {

	public static boolean validateNull(Object ruleRepVo) {
		// TODO Auto-generated method stub
		if(ruleRepVo instanceof RuleReportVO)
		{
			RuleReportVO r=(RuleReportVO) ruleRepVo;
			if(ruleRepVo!=null && r.getInvokeCodePath()!=null
					&& r.getPatternPath()!=null && r.getPojoPath()!=null)
			{
				return true;
			}
		}
		return false;
	}
	
}
