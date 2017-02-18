package com.example.try_prince_run.utils;

//import com.example.try_prince_run.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.try_gameengine.avg.GraphicsUtils;
import com.example.try_gameengine.framework.LightImage;
import com.example.try_prince_run.R;

public class BitmapUtil {

	static Context context;

	public static Bitmap bg1;
	public static Bitmap bg2;
	public static Bitmap chair02;
	public static Bitmap chair02_1;
	public static Bitmap chair02_2;
	public static Bitmap chair02_3;
	public static Bitmap chair02_4;
	public static Bitmap lemon;
	public static Bitmap grapes;
	public static Bitmap orange;
	public static Bitmap watermelon;

//	public static Bitmap redPoint, bluePoint;
	public static Bitmap yellowPoint;
	public static Bitmap greenPoint;
	public static Bitmap mapBg1;

	public static Bitmap[] jewelBitmaps;
	
	public static Bitmap hamster;
	
//	public static Bitmap bg;
//	public static Bitmap flower;
	public static Bitmap fireball;
	public static Bitmap cloud1;
	public static Bitmap cloud2;
	public static Bitmap cloud3;
	
	public static Bitmap restartBtn01;
	public static Bitmap restartBtn02;
	public static Bitmap gameover;
	
	public static Bitmap sheep;
	public static Bitmap sheepJump;
	public static Bitmap sheepJump2;
	public static Bitmap sheepJump3;
	
	public static LightImage Comic1, Comic2_1, Comic2_2, Comic2;
	public static Bitmap MainMenuBg;
	public static Bitmap Click_Screen;
	
	//Monster Attack
	public static Bitmap Monster1_Attack1;
	public static Bitmap Monster1_Attack2;
	public static Bitmap Monster1_Attack3;
	public static Bitmap Monster1_Attack4;
	
	//Monster Run
	public static Bitmap Monster1_Run1;
	public static Bitmap Monster1_Run2;
	public static Bitmap Monster1_Run3;
	public static Bitmap Monster1_Run4;
	
	//Monster Catch Hero
	public static Bitmap Monster1_Catch1;
	public static Bitmap Monster1_Catch2;
	public static Bitmap Monster1_Catch3;
	public static Bitmap Monster1_Catch4;
	
	//Monster Hurt
	public static Bitmap Monster1_Hurt;
	
	//Gun Effect
	public static Bitmap Attack_Gun1;
	public static Bitmap Attack_Gun2;
	public static Bitmap Attack_Gun3;
	public static Bitmap Attack_Gun4;
	public static Bitmap Attack_Gun5;
	public static Bitmap Attack_Gun6;
	
	public static int sheepHW = 250;
	
	public static void initBitmap(Context context) {
		if(BitmapUtil.context==null){
			BitmapUtil.context = context;
			initBitmap();
		}	
	}

	private static void initBitmap() {
		// redPoint = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.red_point, null);
		 greenPoint = BitmapFactory.decodeResource(context.getResources(),
		 R.drawable.green_point);
		// blackPoint = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.black_point);
		// whitePoint = BitmapFactory.decodeResource(context.getResources(),
		// R.drawable.white_point);
		// bluePoint =
		// createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.blue_point),
		// 200, 200);

		BitmapFactory.Options options = new BitmapFactory.Options();
		// Make sure it is 24 bit color as our image processing algorithm
		// expects this format
		options.inPreferredConfig = Config.ARGB_8888;
		// �]�w���n�۰�scale�Ϥ�
		options.inScaled = false;
		
		mapBg1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_launcher, options);

		Comic1 = new LightImage(GraphicsUtils.loadImage("assets/prince_run/launce_scene/comic/Comic1.png"));
		Comic2_1 = new LightImage(GraphicsUtils.loadImage("assets/prince_run/launce_scene/comic/Comic2_1.png"));
		Comic2_2 = new LightImage(GraphicsUtils.loadImage("assets/prince_run/launce_scene/comic/Comic2_2.png"));
		Comic2 = new LightImage(GraphicsUtils.loadImage("assets/prince_run/launce_scene/comic/Comic2.png"));
		MainMenuBg = GraphicsUtils.loadImage("assets/prince_run/title_scene/Title.png");
		Click_Screen = GraphicsUtils.loadImage("assets/prince_run/title_scene/Click Screen.png");
		
		Monster1_Attack1 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Wind/Monster1_Attack_Wind_1.png");
		Monster1_Attack2 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Wind/Monster1_Attack_Wind_2.png");
		Monster1_Attack3 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Wind/Monster1_Attack_Wind_3.png");
		Monster1_Attack4 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Wind/Monster1_Attack_Wind_4.png");
		
		Monster1_Run1 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Run/Monster01_Run_01.png");
		Monster1_Run2 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Run/Monster01_Run_02.png");
		Monster1_Run3 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Run/Monster01_Run_03.png");
		Monster1_Run4 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Run/Monster01_Run_04.png");
		
		Monster1_Catch1 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Catch/Monster01_Catch_01.png");
		Monster1_Catch2 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Catch/Monster01_Catch_02.png");
		Monster1_Catch3 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Catch/Monster01_Catch_03.png");
		Monster1_Catch4 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Catch/Monster01_Catch_04.png");
		
		Monster1_Hurt = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Hurt/Monster01_Hurt_01.png");
		
		Attack_Gun1 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_1.png");
		Attack_Gun2 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_2.png");
		Attack_Gun3 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_3.png");
		Attack_Gun4 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_4.png");
		Attack_Gun5 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_5.png");
		Attack_Gun6 = GraphicsUtils.loadImage("assets/prince_run/animation/Monster01/Attack/Gun/Monster1_Gun_6.png");
		
		BitmapUtil.sheep = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.sheep1), sheepHW, sheepHW);
		BitmapUtil.sheepJump = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.sheep_jump1), sheepHW, sheepHW);
		BitmapUtil.sheepJump2 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.sheep_jump2), sheepHW, sheepHW);
		BitmapUtil.sheepJump3 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.sheep_jump3), sheepHW, sheepHW);
		
		hamster = BitmapUtil.createSpecificSizeBitmap(
				context.getResources().getDrawable(
						R.drawable.hamster), 150*7, 150*2);
		
		bg1 =  GraphicsUtils.loadImage("assets/prince_run/map/Map01/Map1_Background.png");
		bg2 =  GraphicsUtils.loadImage("assets/prince_run/map/Map01/Map1_Floor.png");
		
//		flower = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.bgfood_hd), CommonUtil.screenWidth, (int) (CommonUtil.screenHeight/4.0f));
		
		fireball = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.fireball), 150, 200);
//		
		cloud1 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c1_hd), 250, 150);
		cloud2 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c2_hd), 300, 200);
		cloud3 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.c3_hd), 350, 150);
//		
		restartBtn01 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_restart_btn01), 350, 200);
		restartBtn02 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_restart_btn02), 350, 200);
//		
		gameover = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.game_over), CommonUtil.screenWidth, (int) (CommonUtil.screenWidth/6.0f));
		
		createJewelBitmaps(100, 100);
	}

	// ��l�Ʀn������I
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width,
			int height) {
		// �s�ؤ@��bitmap�A��e20�A�ϥ�ARGB_8888�]�w�A��bitmap�{�b�ť�bitmap��Dnull�C
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // �s�صe���A�Ϊť�bitmap��e��
		drawable.setBounds(0, 0, width, height);// �]�wdrawable�����(��Ϥ�ۤv����e)
		drawable.draw(canvas); // �b�e���W�e�W��drawable(����bitmap�w�g�Q�e�W�F��A���O�ťդF)
		return bitmap;
	}

	public static void createJewelBitmaps(int w, int h) {
		jewelBitmaps = new Bitmap[] {
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.orange_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.yellow_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.green_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.blue_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.brown_point), w, h) };
	}
}
