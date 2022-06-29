import React from 'react';
import SearchIcon from '@material-ui/icons/Search';
import './styles.css';

export default function SearchBar({ value, changeInput }) {
  return (
    <div className="searchBar-wrap">
      <SearchIcon className='searchBar-icon'/>
      <input 
        type="text" 
        placeholder="Genshin Impact" 
        value={value} 
        onChange={changeInput}
      />
    </div>
  )
}
