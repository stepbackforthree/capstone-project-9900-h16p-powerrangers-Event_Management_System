import { message } from 'antd';
import { API_HOST } from './apiHost';

/**
 * @author Chen Yanfeng
 * @param {string} url
 * @param {object} opt
 * @returns
 */
export default async function apiCall (url, opt = {}) {
  const { method, data, token } = opt;

  const init = {
    method: method,
    headers: {
      'Content-Type': 'application/json',
      Authorization: `${token || localStorage.getItem('token')}`,
    },
    body: method !== 'GET' ? JSON.stringify(data) : undefined,
  }


  const res = await fetch(`${API_HOST}${url}`, init);
  console.log(res);

  const dataJson = await res.json();
  console.log('dataJson:', dataJson);


  if (dataJson.error) {
    if (dataJson.error.includes('token is invalid')) {
      console.log(dataJson.error);
      message.error('token expired, please login', 3, () => {
        window.location.href = '/login';
      });
    } else {
      message.error(dataJson.error, 3)
    }
    return;
  }
  return dataJson;
}
