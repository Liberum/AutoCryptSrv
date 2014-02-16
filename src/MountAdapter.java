import java.util.List;

public class MountAdapter {

    List<String> path(String password, String dsk, String dPath) {
	String programPath = "C:\\Program Files\\TrueCrypt\\truecrypt.exe";
	List<String> params = java.util.Arrays.asList(programPath, "/v", dPath,
		"/l", dsk, "/p", password, "/e", "/b", "/w", "/q");
	return params;

    }

}
