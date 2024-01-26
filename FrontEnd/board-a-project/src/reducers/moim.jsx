const initialState = {
    moimList: []
  };
  
  const moimReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'SET_MOIM_LIST':
        return { ...state, moimList: action.payload };
      default:
        return state;
    }
  };
  
  export default moimReducer;