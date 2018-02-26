package linchange.com.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import linchange.com.core.delegates.AwesomeDelegate;
import linchange.com.core.net.RestClient;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.util.log.AwesomeLogger;
import linchange.com.ec.R;
import linchange.com.ec.R2;

/**
 * Created by lkmc2 on 2017/12/31.
 * 注册页面Fragment
 */

public class SignUpDelegate extends AwesomeDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null; //名字输入框

    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null; //邮箱输入框

    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null; //手机输入框

    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null; //密码输入框

    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null; //重复密码输入框

    private ISignListener mSignListener = null; //登陆注册监听器

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ISignListener) { //activity实现了登陆注册监听器的接口
            mSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://or6naol85.bkt.clouddn.com/info.json")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            AwesomeLogger.json("USER_PROFILE", response);
                            AwesomeLogger.e(SignUpDelegate.class.getSimpleName(), response);
                            SignHandler.onSignUp(response, mSignListener); //注册，将json数据持久化到本地
                        }
                    })
                    .build()
                    .post();
            AwesomeLogger.e(SignUpDelegate.class.getSimpleName(), "执行完毕");
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() { //点击登陆链接
        start(new SignInDelegate()); //启动登陆页面
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true; //是否通过

//        if (name.isEmpty()) {
//            mName.setError("请输入姓名");
//            isPass = false;
//        } else {
//            mName.setError(null);
//        }
//
//        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            mEmail.setError("错误的邮箱格式");
//            isPass = false;
//        } else {
//            mEmail.setError(null);
//        }
//
//        if (phone.isEmpty() || phone.length() != 11) {
//            mPhone.setError("手机号码错误");
//            isPass = false;
//        } else {
//            mPhone.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 6) {
//            mPassword.setError("密码不能少于6位");
//            isPass = false;
//        } else {
//            mPassword.setError(null);
//        }
//
//        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
//            mRePassword.setError("两次密码输入不一致");
//            isPass = false;
//        } else {
//            mRePassword.setError(null);
//        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View rootView) {

    }
}
