const validate = values => {
  const errors = {}
  if (!values.year || !values.year.length) {
    errors.year = { _error: 'At least one year must be entered' }
  } else {
    const yearArrayErrors = []
    values.year.forEach((year, yearIndex) => {
      const memberErrors = {}
      if (!year || !member.year) {
        yearErrors.year = 'Required'
        yearArrayErrors[yearIndex] = yearErrors
      }
      if (!member || !member.benefits) {
        yearErrors.benefits = 'Required'
        yearArrayErrors[yearIndex] = yearErrors
      }
      return Errors
    })
    if(yearArrayErrors.length) {
      errors.year = yearArrayErrors
    }
  }
  return errors
}

export default validate