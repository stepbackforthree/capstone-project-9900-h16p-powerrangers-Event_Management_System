import React, { useState, useEffect } from 'react';
import SearchBar from '../../components/Home/SearchBar';
import FilterPanel from '../../components/Home/FilterPanel';
import List from '../../components/Home/List';
// import EmptyView from '../../components/Home/EmptyView';
import { dataList } from '../../constants/index';
import './style.css';
import request from '../../utils/request';


export default function DashBoard() {

  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedRating, setSelectedRating] = useState(null);
  const [selectedPrice, setSelectedPrice] = useState([100,1000]);
  const [cities, setCities] = useState([
    {
      id:1,
      checked:false,
      label:'Sydney',
    },
    {
      id:2,
      checked:false,
      label:'Melbourne',
    },
    {
      id:3,
      checked:false,
      label:'Queensland',
    },
  ]);

  const [list, setList] = useState(dataList);

  useEffect(() => {
    request('/events/getAllEvents',{
      method: 'POST',
      data: {

      }
    }).then((data) => {
      console.log(data);
    })
  }, []);


  const handleSelectCategory = (event, value) => {
    console.log(event, value);
    if (!value) {
      // console.log(event, value);
      setSelectedCategory(null);
    } else {
      setSelectedCategory(value);
    }
  };

  const handleSelectRating = (event, value) => !value ? null : setSelectedRating(value);

  const handleChangeChecked = id => {
    const citiesStateList = cities;
    const changeCheckedCities = citiesStateList.map((item)=>
      item.id === id ? {...item, checked:!item.checked} : item
    );

    setCities(changeCheckedCities);
  };
  
  const handleChangePrice = (event, value) => {
    // console.log(event, value);
    setSelectedPrice(value);
  }

  const handleSearchContent = () => {
    console.log(selectedCategory);
    console.log(selectedRating);
    console.log(selectedPrice);
    console.log(cities);
  }

  return (
    <div className='home'>
      {/* slide */}
      <SearchBar />

      <div className="home_panelList-wrap">
        <div className="home_panel-wrap">
          {/* filter */}
          <FilterPanel 
            selectToggle={handleSelectCategory} 
            selectedCategory={selectedCategory}
            selectRating={handleSelectRating}
            selectedRating={selectedRating}
            cities={cities}
            changeChecked={handleChangeChecked}
            selectedPrice={selectedPrice}
            changePrice={handleChangePrice}
            handleSearchContent={handleSearchContent}
          />
        </div>
        <div className="home_list-wrap">
          {/* list */}
          <List list={list}  />
        </div>
      </div>
    </div>
  )
}
