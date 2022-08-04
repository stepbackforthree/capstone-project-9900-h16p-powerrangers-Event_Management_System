import React, { useState, useEffect } from 'react';
import SearchBar from '../../components/Home/SearchBar';
import FilterPanel from '../../components/Home/FilterPanel';
import List from '../../components/Home/List';
// import EmptyView from '../../components/Home/EmptyView';
// import { dataList } from '../../constants/index';
import './style.css';
import request from '../../utils/request';


export default function DashBoard() {

  const [selectedCategory, setSelectedCategory] = useState(null);
  const [chooseCategory, setChooseCategory] = useState();
  const [searchValue,setSearchValue] = useState();
  const [selectedRating, setSelectedRating] = useState(null);
  const [selectedPrice, setSelectedPrice] = useState([0,1000]);
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

  const eventTypeMap = {
    'concert': 1,
    'sports': 2,
    'comic and animation': 3,
    'parents-child campaign': 4,
    'tourism exhibition': 5
  }

  const [list, setList] = useState([]);

  useEffect(() => {
    request('/events/getAllEvents',{
      method: 'POST',
      data: {}
    }).then((data) => {
      // console.log(data);
      setList(data);
    })
  }, []);


  const handleSelectCategory = (event, value) => {
    console.log('choose:', value);
    if (!value) {
      // console.log(event, value);
      setSelectedCategory(null);
      setChooseCategory(null);
    } else {
      const chioce = eventTypeMap[value.toLowerCase()];
      console.log('chioce:', chioce);
      setSelectedCategory(value);
      setChooseCategory(chioce);
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

  const createCitiesList = (cities) => {
    let res = [];
    for (var i=0; i<cities.length; i++) {
      if (cities[i].checked === true) {
        res.push(cities[i].label);
      }
    }
    return res;
  }

  const handleSearchContent = () => {
    const citiesList = createCitiesList(cities);
    const data = {
      "location": citiesList,
      "maxPrice": selectedPrice[1],
      "minPrice": selectedPrice[0],
      "eventType": chooseCategory,
      "starLevel": selectedRating
    }
    console.log(data);
    request('/events/getAllEvents', {
      method: 'POST',
      data: data
    }).then((response) => {
      console.log(response);
      setList(response);
    })
  }

  const changeSearchInput = (e) => {
    setSearchValue(e.target.value);
  }
  
  const searchEvents = () => {
    request(`/events/searchEvents?keyWords=${searchValue}`, {
      method: 'GET',
      data: {}
    }).then((response) => {
      // console.log(response);
      setList(response);
    })
  }

  const handleRecommendationSearch = () => {
    request(`/events/getRecommendation`, {
      method: 'GET',
      data: {}
    }).then((response) => {
      setList(response);
    })
  }

  const handleOneMonthSearch = () => {
    request(`/events/getOneMonthEvents`, {
      method: 'GET',
      data: {}
    }).then((response) => {
      setList(response);
    })
  }

  return (
    <div className='home'>
      {/* slide */}
      <SearchBar 
        searchValue={searchValue}
        changeSearchInput={changeSearchInput}
        searchEvents={searchEvents}
      />

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
            handleRecommendationSearch={handleRecommendationSearch}
            handleOneMonthSearch={handleOneMonthSearch}
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
