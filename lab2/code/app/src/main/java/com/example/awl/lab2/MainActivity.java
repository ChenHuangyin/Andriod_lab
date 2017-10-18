package com.example.awl.lab2;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.String;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] LIST_VIEW={"拍摄","从相册选择"};
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("上传头像").setItems(LIST_VIEW, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0) Toast.makeText(getApplicationContext(),"你选择了[拍摄]",Toast.LENGTH_SHORT).show();
                else if(which == 1) Toast.makeText(getApplicationContext(),"你选择了[从相册选择]",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which) {
                Toast.makeText(getApplicationContext(),"你选择了取消",Toast.LENGTH_SHORT).show();
            }
        }).create();

        ImageView Image = (ImageView)findViewById(R.id.image);
        Image.setFocusable(true);
        if (Image != null)
        {
            Image.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View V){
                    alertDialog.show();
                }
            });
        }

        final Button Student = (Button)findViewById(R.id.id1);
        final Button Teacher = (Button)findViewById(R.id.id2);

        Student.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Student.setSelected(true);
                Teacher.setSelected(false);
                Snackbar.make(v,"你选择了学生",Toast.LENGTH_LONG)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
        });

        Teacher.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Teacher.setSelected(true);
                Student.setSelected(false);
                Snackbar.make(v,"你选择了教职工",Toast.LENGTH_LONG)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
        });

        final TextInputLayout ID = (TextInputLayout)findViewById(R.id.ed1);
        final EditText IDedt = ID.getEditText();
        final TextInputLayout PASSWORD = (TextInputLayout)findViewById(R.id.password);
        final EditText PASSedt = PASSWORD.getEditText();

        Button Sign_in = (Button)findViewById(R.id.sign_in);
        Button Sign_up = (Button)findViewById(R.id.sign_up);

        Sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                if(IDedt.getText().toString().length() == 0){
                    ID.setErrorEnabled(true);
                    ID.setError("学号不能为空");
                    PASSWORD.setErrorEnabled(false);
                }
                else if(PASSedt.getText().toString().length() == 0){
                    PASSWORD.setErrorEnabled(true);
                    PASSWORD.setError("密码不能为空");
                    ID.setErrorEnabled(false);
                }
                else{
                    String Res;
                    ID.setErrorEnabled(false);
                    PASSWORD.setErrorEnabled(false);
                    if(IDedt.getText().toString().equals("123456789") && PASSedt.getText().toString().equals("123456789")){
                        Res = "登录成功";
                    }
                    else
                        Res = "登录失败";
                    Snackbar.make(V,Res,Toast.LENGTH_LONG)
                            .setAction("确定",new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        });


        Sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String Res="";
                if(Teacher.isSelected()) Res="教职工";
                else if (Student.isSelected()) Res="学生";
                Snackbar.make(v,Res+"注册功能未启用",Toast.LENGTH_LONG)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
        });
    }
}
