import React, { useEffect, useState } from 'react';
import ProfileForm from '../../components/ProfileForm';
import request from '../../utils/request';


export default function ProfilePage() {
  const [userDetail, setUserDetail] = useState('');

  useEffect(() => {
    request('/users/queryUser',{
      method: 'GET'
    }).then(data => {
      console.log(data);
      setUserDetail(data);
    })
  }, []);

  // const userDetail = {
  //   'username': 'doom slayer',
  //   'password': 'ds456',
  //   'nickname': 'pefecto',

  // }

  return (
    <div>
      <ProfileForm userDetail={userDetail}/>
    </div>
  )
}
