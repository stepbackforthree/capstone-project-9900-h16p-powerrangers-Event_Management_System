import React, { useState, useEffect } from 'react';
import SearchIcon from '@material-ui/icons/Search';
import './styles.css';
import { SearchOutlined } from '@ant-design/icons';
import { Button, Tooltip } from 'antd';
import request from '../../../utils/request';

export default function SearchBar({searchValue, changeSearchInput, searchEvents}) {
  // const [searchValue,setSearchValue] = useState();

  // const changeSearchInput = (e) => {
  //   setSearchValue(e.target.value);
  // }
  
  // const searchEvents = () => {
  //   request(`/events/searchEvents?keyWords=${searchValue}`, {
  //     method: 'GET',
  //     data: {}
  //   }).then((response) => {
  //     console.log(response);
  //   })
  // }
  
  return (
    <div className="searchBar-wrap">
      <SearchIcon className='searchBar-icon'/>
      <input 
        type="text" 
        placeholder="Genshin Impact" 
        value={searchValue} 
        onChange={changeSearchInput}
      />
      <Tooltip title="search">
        <Button 
          shape="circle" 
          icon={<SearchOutlined />} 
          onClick={() => searchEvents()}
        />
      </Tooltip>
    </div>
  )
}
