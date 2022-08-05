import React from 'react';
import SearchIcon from '@material-ui/icons/Search';
import './styles.css';
import { SearchOutlined } from '@ant-design/icons';
import { Button, Tooltip } from 'antd';


export default function SearchBar({searchValue, changeSearchInput, searchEvents}) {

  
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
