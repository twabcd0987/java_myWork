package tw.StudentManager;

import java.security.MessageDigest;

public class MD5 {
	 /**
     * 方法1
     * 
     * @param source
     * @return 16进制整数字符串格式
     */
    public static String getMD5(byte[] source)
    {
        String s = null;
        char hexDigits[] =
        { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f'
        };
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5"); 
            md.update(source); 
            // MD5 的计算结果是一个 128 位的长度整数， 
            byte tmp[] = md.digest(); 

            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++)
            {   // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移（即无符号右移），将符号位一起右移

                // 取字节中低 4 位的数字转换 
                str[k++] = hexDigits[byte0 & 0xf]; 
            }
            s = new String(str); // 换后的结果转换为字符串

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return s;
    }

	/*public static byte[] toMD5(byte[] b) throws Exception {
		 MessageDigest md5 = MessageDigest.getInstance("MD5"); 
			md5.update(b); 
		//System.out.print(md5);
			return md5.digest();
		}*/
	//测试函数
	public static void main(String args[]){
		try {
			String ss="b8c37e33defde51cf91e1e03e51657da";
			String str1_md5=new String(MD5.getMD5("1002".getBytes()));
			
				System.out.println(str1_md5);
		
			}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}	
