package sem3.customerdemo.utils;
// import x
public class SimpleSanitizer {


    public static String sanitize(String input){
        String temp = input.replace("<script>","script");
        temp = temp.replace("</script>","script");
        return temp;
    }
}
