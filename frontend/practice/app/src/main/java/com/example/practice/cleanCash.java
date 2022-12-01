package com.example.practice;

import android.content.Context;

import java.io.File;

public class cleanCash {
    public static void clearAppData(Context context) {
        File cache = context.getCacheDir();  //캐시 폴더 호출
        File appDir = new File(cache.getParent());  //App Data 삭제를 위해 캐시 폴더의 부모폴더까지 호출
        if(appDir.exists()) {
            String[] children = appDir.list();
            for(String s : children) {
                //App Data 폴더의 리스트를 deleteDir 를 통해 하위 디렉토리 삭제
                deleteDir(new File(appDir, s));
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if(dir != null && dir.isDirectory()) {
            String[] children = dir.list();

            //파일 리스트를 반복문으로 호출
            for(int i=0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success) {
                    return false;
                }
            }
        }

        //디렉토리가 비어있거나 파일이므로 삭제 처리
        return dir.delete();
    }
}
