package com.erstens.shump.util;

public class HumpUtil {
    private final static String SPLIT = "_" ;
    private static HumpUtil instance = new HumpUtil() ;

    private HumpUtil() {}
    public static String format(String s) {
        if(null == s || "".equals(s))
            throw new IllegalArgumentException("Param is null .") ;

        return s.indexOf(SPLIT) > -1 ? instance.toHump(s) : instance.fromHump(s) ;
    }

    private String toHump(String s) {
        StringBuffer buf = new StringBuffer() ;
        String [] split = s.split(SPLIT) ;
        for (int i = 0; i < split.length; i++) {
            String item = split[i] ;

            if("".equals(item))
                continue ;

            char c = item.charAt(0);

            //lower letter,must be not first .
            if(0 != i && c > 0x60 && c < 0x7B) {
                buf.append((c + "").toUpperCase() + item.substring(1)) ;
                continue ;
            }
            buf.append(item) ;
        }

        return buf.toString() ;
    }
    private String fromHump(String s) {
        StringBuffer buf = new StringBuffer() ;
        char [] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i] ;

            //upper letter,must be not first .
            if(0 != i && c > 0x40 && c < 0x5B) {
                buf.append(SPLIT + (c + "").toLowerCase()) ;
                continue ;
            }
            //start from 'UpperCase' .
            if(0 == i && c > 0x40 && c < 0x5B) {
                buf.append(SPLIT) ;
                buf.append((c + "").toLowerCase()) ;
                continue ;
            }
            buf.append(c) ;
        }
        return buf.toString() ;
    }
}
