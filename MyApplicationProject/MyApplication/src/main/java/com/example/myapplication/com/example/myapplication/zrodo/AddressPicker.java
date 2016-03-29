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

    private String[] provinceDatas={"����","����"};
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
         * ����
         */
        String[] js={"�Ͼ�","��ͨ","����"};
        String[] nj={"������","������","�껨��","������"};
        String[] nt={"���","ͨ����","��բ��","����","����","�綫"};
        String[] sz={"������","�����","�⽭��","��ҵ԰��","������"};
        /**
         * ����
         */
        String[] fj={"����","����"};
        String[] xm={"˼����","������","������","������"};
        String[] fz={"��¥��","��ɽ��","������","��β��"};

        citiesDatas.put("����",js);
        coutiesDatas.put("�Ͼ�",nj);
        coutiesDatas.put("��ͨ",nt);
        coutiesDatas.put("����",sz);

        citiesDatas.put("����",fj);
        coutiesDatas.put("����",xm);
        coutiesDatas.put("����",fz);
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
            cities=new String[]{"Ĭ�ϳ���"};
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
            counties=new String[]{"Ĭ������"};
        }
        countyWheel.setViewAdapter(new ArrayWheelAdapter<String>(context,counties));
        countyWheel.setCurrentItem(0);
    }
}
