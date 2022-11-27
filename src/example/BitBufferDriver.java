package example;

import domain.core.BitBufferAbstract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 */
public class BitBufferDriver {
			
	/**
	 * @param args N/A
	 * @throws IOException for IO.
	 */
	public static void main(String[] args) throws IOException {
		BitBufferAbstract buff = new BitBuffer() ;
		byte[] bytes = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultricies tristique nulla aliquet enim. Id venenatis a condimentum vitae sapien. Diam ut venenatis tellus in metus. Placerat in egestas erat imperdiet sed euismod nisi porta. Non diam phasellus vestibulum lorem sed risus ultricies tristique nulla. Malesuada fames ac turpis egestas sed tempus. A iaculis at erat pellentesque adipiscing commodo elit. Ipsum suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Vitae tempus quam pellentesque nec nam aliquam sem et. Volutpat sed cras ornare arcu dui vivamus arcu. Et tortor consequat id porta nibh venenatis cras sed. Auctor augue mauris augue neque gravida. Et sollicitudin ac orci phasellus egestas tellus rutrum. Luctus accumsan tortor posuere ac ut consequat semper viverra nam. "
		 .getBytes();

		byte[] bytes1 = buff.pack(bytes);
		String fileName = args[0];
		Files.write(Paths.get(fileName), bytes1);

		Path path = Paths.get("byte_array.malc");
		byte[] bytes2 = Files.readAllBytes(path);

		byte[] unpackOutput = buff.unpack(bytes2);
		for (byte b : unpackOutput) {
			System.out.println(b);
		}

	}
}
