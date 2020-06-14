package com.severgames.lib;

public interface ClickListener {

    enum POSITION_HORIZONTAL{
        Center,
        LeftBottom,
        RightBottom,
        LeftCenter,
        RightCenter
    }
    enum POSITION_VERTICAL{
        Center,
        UpBottom,
        DownBottom,
        UpCenter,
        DownCenter
    }


    void click(String id);
}
