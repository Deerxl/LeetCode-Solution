import java.util.regex.Pattern;

public class MathTag {

    /**
     * 整数反转 -easy 先 x 10 后 / 10 判断前后是否相等，处理溢出
     * @param x
     * @return
     */
    public int reverse(int x) {
        /**
         * error x为十进制，非十六进制

         x = Integer.parseInt(Integer.toHexString(x));
         if (((x & 0x00000008) >> 3 == 0x1) || x == 0) return 0;
         int sign = (x >> 31 & 0x1) << 31;
         int result = 0;

         result = (x << 28) & (0x70000000 | sign);
         result = (x << 20) & (0x0f000000) | result;
         result = (x << 12) & (0x00f00000) | result;
         result = (x << 4) & (0x000f0000) | result;
         result = (x >> 4) & (0x0000f000) | result;
         result = (x >> 12) & (0x00000f00) | result;
         result = (x >> 20) & (0x000000f0) | result;
         result = (x >> 28) & (0x00000007) | result;

         int count = 0;
         for (int i = 0; i < 7; i++) {
         int zero = (result >> (i * 4)) & 0xf;
         if (zero == 0) count++;
         }

         result = result >> (count * 4);

         return Integer.parseInt(String.valueOf(result) ,16);
         */

        /** 转成long，计算后判断是否溢出 再转回int，比较麻烦
        int count = 0;
        boolean sign = x < 0;
        x = Math.abs(x);
        long tmp = 0;
        int[] nums = new int[1024];
        while (x > 0) {
            nums[count++] = x % 10;
            x /= 10;
        }
        for (int i = 0; i < count; i++) {
            tmp = tmp * 10 + nums[i];
        }
        x = (int) tmp;
        if (sign) {
            tmp = -tmp;
            x = -x;
        }
        if (tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE) return 0;

        return x;
         */

        int result = 0;
        while (x != 0) {
            if ((result * 10) / 10 != result) {
                return 0;
            }
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result;
    }

    /**
     * 求字符串的第一个连续整数 -medium 注意边界值
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0;
        char c = str.charAt(i);
        while (c == ' ' && i < str.length() - 1) c = str.charAt(++i);
        if ((c == '-' || c == '+') && i < str.length() - 1 && digit(str.charAt(i + 1)) || digit(c)) {
            boolean negSign = c == '-';
            long result = 0;        //换成long处理溢出更方便
            if (c == '-' || c == '+') i++;
            while (i < str.length() && digit(str.charAt(i))) {
                if ((result * 10) / 10 == result && result < Integer.MAX_VALUE && result > Integer.MIN_VALUE) {
                    result = result * 10 + (int) str.charAt(i) - 48;
                    i++;
                } else{
                    break;/*else {
                    if (negSign) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }*/
                }
            }
            // result >= 0
            if (result > Integer.MAX_VALUE && !negSign) return Integer.MAX_VALUE;
            if (result <= Integer.MAX_VALUE && !negSign) return (int) result;


            if (result > Integer.MAX_VALUE && negSign) return Integer.MIN_VALUE;

            return negSign ? -(int) result : (int) result;

            /*StringBuffer buf = new StringBuffer();
            buf.append(c);
            buf.append(str.charAt(++i));



            while (i < str.length() && str.charAt(i) == '0') i++;

            int count = 0;
            while (i < str.length() - 1 && digit(str.charAt(++i)) && count++ < 11) {
                buf.append(str.charAt(i));
            }
            long l = Long.parseLong(buf.toString());
            if (l > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (l < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return (int) l;
            }*/
        } else {
            return 0;
        }

    }

    boolean digit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 求整数是否是对称的 -easy 求相反序列的数 和 原数 是否相等
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reverse = 0;
        int temp = x;
        while (temp > 0) {
            reverse = reverse * 10 + temp % 10;
            temp /= 10;
        }
        return reverse == x;
    }

    /**
     * 把int转转为罗马表示数字 -medium
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer buffer = new StringBuffer();
        int[] models = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] values = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < models.length; i++) {
            int count = num / models[i];
            num %= models[i];
            if (count == 0) continue;
            while (count > 0) buffer.append(values[i]);
        }

        return buffer.toString();

        /** 比较麻烦。。考虑多种可能性
         *

        StringBuffer buffer = new StringBuffer();
        int[] models = new int[]{1000, 500, 100, 50, 10, 5, 1};
        String[] values = new String[] {"M", "D", "C", "L", "X", "V", "I"};

        for (int i = 0; i < models.length; i++) {
            if ((i == 1 || i == 3 || i == 5) && (num / models[i + 1] == 4 || num / models[i + 1] == 9)) {     //需要进行替换的
                buffer.append(values[i + 1]);
                if (num / models[i + 1] == 4) {
                    buffer.append(values[i]);
                } else {
                    buffer.append(values[i - 1]);
                }
                num %= models[++i];
                continue;
            }

            int count = num / models[i];
            if (count == 0) {
                num %= models[i];
                continue;
            }
            while (count-- > 0) buffer.append(values[i]);

            num %= models[i];

        }
        return buffer.toString();
         */
    }

    /**
     * 求除法运算结果 -medium  注意边界！！可以换成正数用移位运算
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        int result = 0;
        boolean negDiv1 = dividend < 0;
        boolean negDiv2 = divisor < 0;

        if (divisor == 1)
            return dividend;
        else if (divisor == -1)
            return (dividend == 0x80000000) ? 0x7fffffff : -dividend;
        else if (divisor == 0x80000000)
            return (dividend == 0x80000000) ? 1 : 0;

        divisor = Math.abs(divisor);

        int Div2 = divisor;

        if (dividend == 0x80000000) {
            result = 1;
            while (dividend + Div2 < 0 && Div2 << 1 > 0) {
                //Div2 = divisor;
                //if () {
                result = result << 1;
                Div2 = Div2 << 1;
                //}

            }
            dividend += Div2;
        }

        dividend = Math.abs(dividend);

        while (dividend >= divisor) {
            Div2 = divisor;
            int tmpResult = 1;
            while (Div2 << 1 > 0 && Div2 << 1 < dividend) {
                Div2 = Div2 << 1;
                tmpResult = tmpResult << 1;
            }
            result += tmpResult;
            dividend -= Div2;
        }

        return (negDiv1 == negDiv2) ? result : -result;
    }
}
