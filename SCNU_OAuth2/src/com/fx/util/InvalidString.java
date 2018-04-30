package com.fx.util;
public class InvalidString {
/**
 * 
*		@Method: isNotEmptyBatch 
*		@Description:  批量判断字符串是否为空
*		@param  不限参数
*		@return boolean
*		@author FollowFire
*		@date 2018年4月2日 下午5:01:28
 */	
	public static boolean isNotEmptyBatch(String... strs){  
        for (String str : strs) {  
            if(str==null || "".equals(str))  
                return false;  
        }  
        return true;  
    }  	
	public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
}
	//测试案例
	/**
	 * 
	*		@Method: main 
	*		@Description: 测试方法：isNotEmptyBatch（）用于批量判断字符串是否为空
	*		@param  
	*		@return void
	*		@author FollowFire
	*		@date 2018年4月2日 下午5:12:40
	 */
	public static void main(String[] args) {
		//输入不限个数字符串，返回布尔值（true案例）
		  Boolean flag1 = isNotEmptyBatch("1","2","3");
		  System.out.println(flag1);
		  
		  //输入不限个数字符串，返回布尔值（false案例）
		  Boolean flag2= isNotEmptyBatch("1","2","");
		  System.out.println(flag2);	  
	}
}
