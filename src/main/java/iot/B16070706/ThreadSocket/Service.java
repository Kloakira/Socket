package iot.B16070706.ThreadSocket;

public interface Service {
	/**参数：	userData:UserData对象
	 *		phoneNum：所查询的手机号码
	 *		i：操作类型
	 * */
	public abstract String setNumState(String phoneNum,int i);
}
