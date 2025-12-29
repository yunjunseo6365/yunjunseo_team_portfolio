import '../App.css';
import React, {useEffect, useState} from 'react';
import HeaderLoginForm from '../components/HeaderLoginForm';
import HeaderNotLoginForm from '../components/HeaderNotLoginForm';
import { useLocation } from 'react-router-dom';
import { checkLogin as checkLoginStatus } from '../components/common/authUtils';

function Header(){
    const [isLogin, setIsLogin] = useState(false);
    const location = useLocation();

    useEffect(() => {
        const fetchLoginStatus = async () => {
            const loggedIn = await checkLoginStatus();
            setIsLogin(loggedIn);
        };

        fetchLoginStatus();
    }, [location]);

    return(
        <div>
            {isLogin ? <HeaderLoginForm/> : <HeaderNotLoginForm/>}
        </div>
    )
}

export default Header;
