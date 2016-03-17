package string;

public class RLE {
	public String encode(String src){
		final char max = 2;
		StringBuffer dst = new StringBuffer();
		char runLength = 1;
		char prevChar = src.charAt(0);
		for(int i = 1; i < src.length(); i++){
			char cur = src.charAt(i);
			if(cur == prevChar && runLength < max){
				runLength++;
			} else {
				dst.append(prevChar);
				dst.append(runLength);
				prevChar = cur;
				runLength = 1;
			}
		}
		dst.append(prevChar);
		dst.append(runLength);
		return dst.toString();
	}
	public String decode(String src){
		StringBuffer dst = new StringBuffer();
		for(int i = 0; i < src.length(); i = i + 2){
			char cur = src.charAt(i);
			char count = src.charAt(i+1);
			while(count > 0){
				dst.append(cur);
				count--;
			}
		}
		return dst.toString();
	}
	public static void main(String[] args){
		char a = 255;
		int b = a;
		RLE rle = new RLE();
		String c = "Hello";
		String enc = rle.encode("helllo");
		System.out.println(enc);
		System.out.println(rle.decode(enc));
	}
}
