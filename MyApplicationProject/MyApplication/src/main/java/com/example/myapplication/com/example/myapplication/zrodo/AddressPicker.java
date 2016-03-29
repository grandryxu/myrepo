package com.example.myapplication.com.example.myapplication.zrodo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.myapplication.ArrayWheelAdapter;
import com.example.myapplication.OnWheelChangedListener;
import com.example.myapplication.R;
import com.example.myapplication.WheelView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by grandry.xu on 15-5-18.
 */
public class AddressPicker extends FrameLayout implements OnWheelChangedListener {

    private WheelView provinceWheel;
    private WheelView cityWheel;
    private WheelView countyWheel;

    private String provinceName;
    private String cityName;
    private String countyName;

    private String[] provinceDatas={"江苏","福建"};
    private Map<String,String[]> citiesDatas=new HashMap<String,String[]>();
    private Map<String,String[]> coutiesDatas=new HashMap<String, String[]>();

    public AddressPicker(Context context) {
        this(context,null);
    }

    public AddressPicker(Context context, AttributeSet attrs) {
      this(context,attrs,0);
    }

    public AddressPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initMaps();
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.address_picker,this,true);
        provinceWheel= (WheelView) findViewById(R.id.province);
        cityWheel= (WheelView) findViewById(R.id.city);
        countyWheel= (WheelView) findViewById(R.id.county);
        provinceWheel.setViewAdapter(new ArrayWheelAdapter<String>(context,provinceDatas));
        provinceWheel.addChangingListener(this);
        cityWheel.addChangingListener(this);
        countyWheel.addChangingListener(this);
        provinceWheel.setVisibleItems(5);
        cityWheel.setVisibleItems(5);
        countyWheel.setVisibleItems(5);
        changeCityByProvince(context);





    }

    private void initMaps() {
        /**
         * 江苏
         */
        String[] js={"南京","南通","苏州"};
        String[] nj={"六合区","建邺区","雨花区","江宁区"};
        String[] nt={"如皋","通州区","港闸区","启东","海门","如东"};
        String[] sz={"姑苏区","相城区","吴江区","工业园区","高新区"};
        /**
         * 福建
         */
        String[] fj={"厦门","福州"};
        String[] xm={"思明区","湖里区","集美区","海沧区"};
        String[] fz={"鼓楼区","仓山区","晋安区","马尾区"};

        citiesDatas.put("江苏",js);
        coutiesDatas.put("南京",nj);
        coutiesDatas.put("南通",nt);
        coutiesDatas.put("苏州",sz);

        citiesDatas.put("福建",fj);
        coutiesDatas.put("厦门",xm);
        coutiesDatas.put("福州",fz);
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {

        if(wheel==provinceWheel){
             changeCityByProvince(getContext());
        }
        else if(wheel==cityWheel){
            changeCountyByCity(getContext());
        }else if(wheel==countyWheel){
            countyName=coutiesDatas.get(cityName)[newValue];
        }

    }


    private void changeCityByProvince(Context context){
        int current=provinceWheel.getCurrentItem();
        provinceName=provinceDatas[current];
        String[] cities=citiesDatas.get(provinceName);
        if(cities==null||cities.length<=0){
            cities=new String[]{"默认城市"};
        }
        cityWheel.setViewAdapter(new ArrayWheelAdapter<String>(context,cities));
        cityWheel.setCurrentItem(0);
        changeCountyByCity(context);
    }

    private void changeCountyByCity(Context context) {
        int current=cityWheel.getCurrentItem();
        cityName=citiesDatas.get(provinceName)[current];
        String[] counties=coutiesDatas.get(cityName);
        if(counties==null||counties.length<=0){
            counties=new String[]{"默认区县"};
        }
        countyWheel.setViewAdapter(new ArrayWheelAdapter<String>(context,counties));
        countyWheel.setCurrentItem(0);
    }
}
