import message from './message.js';
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
      // 'Content-Type': 'application/json',
      // Accept: 'application/json',
      Authorization: `Bearer ${token || localStorage.getItem('token')}`,
    },
    // cache: 'no-cache',
    // body: method !== 'GET' ? JSON.stringify(data) : undefined,
    body: method !== 'GET' ? data : undefined,
  }

  // console.log(init);
  // console.log(`${API_HOST}${url}`);

  // const res = await fetch(`${API_HOST}${url}`, init);
  // const dataJson = await res.json();
  const dataJson = await fetch(`${API_HOST}${url}`, init);

  if (dataJson.error) {
    if (dataJson.error.includes('invalid token')) {
      message.error('token expired, please login', 3, () => {
        window.location.href = '/login';
      });
    } else {
      message.error({content: (dataJson.error),  duration: 2500})
    }
    return;
  }
  // console.log('dataJson:', dataJson);
  return dataJson;
}
