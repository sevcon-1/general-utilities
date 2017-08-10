import com.sunopsis.dwg.DwgObject;  
import oracle.odi.core.OdiInstance;
// public class OdiDecrypt {  
   //public OdiDecrypt() {  
//     super();  
   //}  
//   @SuppressWarnings("deprecation")  
//   public static void main(String[] args) {  
//     OdiDecrypt odiDecrypt = new OdiDecrypt();  
     @SuppressWarnings("deprecation")  
     String strMasterPassEnc="ZVfYiLBSOe7Pts/skeZwAA==";  
     String strMasterPass=DwgObject.snpsDecypher(strMasterPassEnc, odiInstance);  
     println(strMasterPass);  
//   }  
 //}  
