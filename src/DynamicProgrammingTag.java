public class DynamicProgrammingTag {
    /*public boolean isMatch(String s, String p) {
        if (s.length() == 0 || p.length() == 0) return false;
        char starRec=' ', schar, pchar;
        boolean starInUse = false;
        for (int i = 0, j = 0; i < s.length() && j < p.length();) {
            schar = s.charAt(i);
            pchar = p.charAt(j);
            //经测试，'*'前必跟'.'或字母
            if (pchar == '*') {
                char back = p.charAt(j - 1);
                if(isCharacter(back)) {
                    starRec = p.charAt(j - 1);
                } else if (back == '.') {
                    starRec = '*';
                }
                starInUse = true;
            }

            if (schar == pchar || pchar == '.'
                    || (pchar == '*' && (starRec == schar || starRec == '*'))) {
                i++;
                j++;
                starInUse = false;
            } else if (){
                //j++;
            }
            //break;
        }
        return false;
    }*/

    boolean isCharacter(char c) {
        return c >= 'a' && c <= 'z';
    }
}
