const HOST = "";

const login = () => {
  return this.$axios.get(`${HOST}/api/getData`)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.error(error);
      throw error;
    });
};

export default {
  getData
};
