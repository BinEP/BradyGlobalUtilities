package utility_classes;

/**
 * 
 * All variables that the classes will use for sizing, names, and other constants for the game
 * 
 * @author Brady
 *
 */
public class Windows {

	private static final int TOP_BUFFER = 22;
	/**
	 * Windows width size
	 */
	private static int WIDTH = 800;
	/**
	 * Windows height size
	 */
	private static int HEIGHT = 480;
	private static int REAL_HEIGHT = HEIGHT + getTopBuffer();
	private static boolean ALWAYS_ON_TOP = false;
	private static boolean RESIZEABLE = true;
	
	
	private static String FONT_NAME = "joystix";
//	private static String FONT_NAME = "tele";
	private static int TITLE_SIZE = 80;
	private static int ENTER_TO_START_SIZE = 26;
	
	private static int PAUSE_SIZE = 60;
	private static int SCORE_SIZE = 40;
	
	private static int YOU_WIN_SIZE = 60;
	private static int SCORE_LIST_SIZE = 17;
	private static int END_SCORE_SIZE = 40;
	private static int YOU_LOSE_SIZE = 60;
	private static int RESTART_SIZE = 20;
	
	private static int TITLE_Y = 180;
	private static int ENTER_Y = 250;
	private static int START_Y = 280;
	private static int PAUSE_Y = 200;
	private static int END_SCORE_Y = 450;
	private static int YOU_LOSE_Y = 170;
	private static int RESTART_Y = 320;
	
	/**
	 * Game name displayed in window bar and on title screen
	 */
//	public static final String GAME_NAME = "Snake";
//
//	/**
//	 * Path of folder where score and people files are
//	 */
//	public static final String FOLDER_PATH = "InfoFiles/";
//	public static final String NAME = "Game Name";
//	public static final String TXT_FILE = NAME.toLowerCase().replaceAll("\\s", "");
//	public static final String FONT_FILE = "joystix";
//	
 	public Windows() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the wIDTH
	 */
	public static int getWidth() {
		return WIDTH;
	}

	/**
	 * @param wIDTH the wIDTH to set
	 */
	public static void setWidth(int wIDTH) {
		WIDTH = wIDTH;
	}

	/**
	 * @return the hEIGHT
	 */
	public static int getHeight() {
		return HEIGHT;
	}

	/**
	 * @param hEIGHT the hEIGHT to set
	 */
	public static void setHeight(int hEIGHT) {
		HEIGHT = hEIGHT;
		setREAL_HEIGHT(HEIGHT + getTopBuffer());
	}

	/**
	 * @return the aLWAYS_ON_TOP
	 */
	public static boolean isALWAYS_ON_TOP() {
		return ALWAYS_ON_TOP;
	}

	/**
	 * @param aLWAYS_ON_TOP the aLWAYS_ON_TOP to set
	 */
	public static void setALWAYS_ON_TOP(boolean aLWAYS_ON_TOP) {
		ALWAYS_ON_TOP = aLWAYS_ON_TOP;
	}

	/**
	 * @return the rESIZEABLE
	 */
	public static boolean isRESIZEABLE() {
		return RESIZEABLE;
	}

	/**
	 * @param rESIZEABLE the rESIZEABLE to set
	 */
	public static void setRESIZEABLE(boolean rESIZEABLE) {
		RESIZEABLE = rESIZEABLE;
	}

	/**
	 * @return the fONT_NAME
	 */
	public static String getFONT_NAME() {
		return FONT_NAME;
	}

	/**
	 * @param fONT_NAME the fONT_NAME to set
	 */
	public static void setFONT_NAME(String fONT_NAME) {
		FONT_NAME = fONT_NAME;
	}

	/**
	 * @return the tITLE_SIZE
	 */
	public static int getTITLE_SIZE() {
		return TITLE_SIZE;
	}

	/**
	 * @param tITLE_SIZE the tITLE_SIZE to set
	 */
	public static void setTITLE_SIZE(int tITLE_SIZE) {
		TITLE_SIZE = tITLE_SIZE;
	}

	/**
	 * @return the eNTER_TO_START_SIZE
	 */
	public static int getENTER_TO_START_SIZE() {
		return ENTER_TO_START_SIZE;
	}

	/**
	 * @param eNTER_TO_START_SIZE the eNTER_TO_START_SIZE to set
	 */
	public static void setENTER_TO_START_SIZE(int eNTER_TO_START_SIZE) {
		ENTER_TO_START_SIZE = eNTER_TO_START_SIZE;
	}

	/**
	 * @return the pAUSE_SIZE
	 */
	public static int getPAUSE_SIZE() {
		return PAUSE_SIZE;
	}

	/**
	 * @param pAUSE_SIZE the pAUSE_SIZE to set
	 */
	public static void setPAUSE_SIZE(int pAUSE_SIZE) {
		PAUSE_SIZE = pAUSE_SIZE;
	}

	/**
	 * @return the sCORE_SIZE
	 */
	public static int getSCORE_SIZE() {
		return SCORE_SIZE;
	}

	/**
	 * @param sCORE_SIZE the sCORE_SIZE to set
	 */
	public static void setSCORE_SIZE(int sCORE_SIZE) {
		SCORE_SIZE = sCORE_SIZE;
	}

	/**
	 * @return the yOU_WIN_SIZE
	 */
	public static int getYOU_WIN_SIZE() {
		return YOU_WIN_SIZE;
	}

	/**
	 * @param yOU_WIN_SIZE the yOU_WIN_SIZE to set
	 */
	public static void setYOU_WIN_SIZE(int yOU_WIN_SIZE) {
		YOU_WIN_SIZE = yOU_WIN_SIZE;
	}

	/**
	 * @return the sCORE_LIST_SIZE
	 */
	public static int getSCORE_LIST_SIZE() {
		return SCORE_LIST_SIZE;
	}

	/**
	 * @param sCORE_LIST_SIZE the sCORE_LIST_SIZE to set
	 */
	public static void setSCORE_LIST_SIZE(int sCORE_LIST_SIZE) {
		SCORE_LIST_SIZE = sCORE_LIST_SIZE;
	}

	/**
	 * @return the eND_SCORE_SIZE
	 */
	public static int getEND_SCORE_SIZE() {
		return END_SCORE_SIZE;
	}

	/**
	 * @param eND_SCORE_SIZE the eND_SCORE_SIZE to set
	 */
	public static void setEND_SCORE_SIZE(int eND_SCORE_SIZE) {
		END_SCORE_SIZE = eND_SCORE_SIZE;
	}

	/**
	 * @return the yOU_LOSE_SIZE
	 */
	public static int getYOU_LOSE_SIZE() {
		return YOU_LOSE_SIZE;
	}

	/**
	 * @param yOU_LOSE_SIZE the yOU_LOSE_SIZE to set
	 */
	public static void setYOU_LOSE_SIZE(int yOU_LOSE_SIZE) {
		YOU_LOSE_SIZE = yOU_LOSE_SIZE;
	}

	/**
	 * @return the rESTART_SIZE
	 */
	public static int getRESTART_SIZE() {
		return RESTART_SIZE;
	}

	/**
	 * @param rESTART_SIZE the rESTART_SIZE to set
	 */
	public static void setRESTART_SIZE(int rESTART_SIZE) {
		RESTART_SIZE = rESTART_SIZE;
	}

	/**
	 * @return the tITLE_Y
	 */
	public static int getTITLE_Y() {
		return TITLE_Y;
	}

	/**
	 * @param tITLE_Y the tITLE_Y to set
	 */
	public static void setTITLE_Y(int tITLE_Y) {
		TITLE_Y = tITLE_Y;
	}

	/**
	 * @return the eNTER_Y
	 */
	public static int getENTER_Y() {
		return ENTER_Y;
	}

	/**
	 * @param eNTER_Y the eNTER_Y to set
	 */
	public static void setENTER_Y(int eNTER_Y) {
		ENTER_Y = eNTER_Y;
	}

	/**
	 * @return the sTART_Y
	 */
	public static int getSTART_Y() {
		return START_Y;
	}

	/**
	 * @param sTART_Y the sTART_Y to set
	 */
	public static void setSTART_Y(int sTART_Y) {
		START_Y = sTART_Y;
	}

	/**
	 * @return the pAUSE_Y
	 */
	public static int getPAUSE_Y() {
		return PAUSE_Y;
	}

	/**
	 * @param pAUSE_Y the pAUSE_Y to set
	 */
	public static void setPAUSE_Y(int pAUSE_Y) {
		PAUSE_Y = pAUSE_Y;
	}

	/**
	 * @return the eND_SCORE_Y
	 */
	public static int getEND_SCORE_Y() {
		return END_SCORE_Y;
	}

	/**
	 * @param eND_SCORE_Y the eND_SCORE_Y to set
	 */
	public static void setEND_SCORE_Y(int eND_SCORE_Y) {
		END_SCORE_Y = eND_SCORE_Y;
	}

	/**
	 * @return the yOU_LOSE_Y
	 */
	public static int getYOU_LOSE_Y() {
		return YOU_LOSE_Y;
	}

	/**
	 * @param yOU_LOSE_Y the yOU_LOSE_Y to set
	 */
	public static void setYOU_LOSE_Y(int yOU_LOSE_Y) {
		YOU_LOSE_Y = yOU_LOSE_Y;
	}

	/**
	 * @return the rESTART_Y
	 */
	public static int getRESTART_Y() {
		return RESTART_Y;
	}

	/**
	 * @param rESTART_Y the rESTART_Y to set
	 */
	public static void setRESTART_Y(int rESTART_Y) {
		RESTART_Y = rESTART_Y;
	}

	/**
	 * @return the rEAL_HEIGHT
	 */
	public static int getREAL_HEIGHT() {
		return REAL_HEIGHT;
	}

	/**
	 * @param rEAL_HEIGHT the rEAL_HEIGHT to set
	 */
	public static void setREAL_HEIGHT(int rEAL_HEIGHT) {
		REAL_HEIGHT = rEAL_HEIGHT;
	}

	/**
	 * @return the topBuffer
	 */
	public static int getTopBuffer() {
		return TOP_BUFFER;
	}
}
